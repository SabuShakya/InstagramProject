package com.users.serviceImpl;
import com.users.dto.UserPhotodto;
import com.users.dto.UserPostDto;
import com.users.model.Likes;
import com.users.model.User;
import com.users.model.UserPhotos;
import com.users.repository.PhotoRepository;
import com.users.service.FollowService;
import com.users.service.LikesService;
import com.users.service.PhotoService;
import com.users.service.UserService;
import com.users.utils.PhotoUtils;
import com.users.utils.UserPhotosPostUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class PhotoServiceImpl implements PhotoService {

   @Resource
   private PhotoRepository photoRepository;

   @Autowired
   private UserService userService;

   @Autowired
   private FollowService followService;

   @Autowired
   private LikesService likesService;

   @Autowired
   private EntityManager entityManager;

    public void savePhoto(UserPhotodto userPhotodto){
        File dir = new File(System.getProperty("catalina.home")+ "/uploads");
        if(!dir.exists()){
            dir.mkdir();
        }
        User user = userService.getUser(userPhotodto.getUsername());
        for (String s:userPhotodto.getImageList()){
            byte[] imageDecoded = Base64.getDecoder().decode(s);
            String filename = imageDecoded.toString();
            String pathToImage = dir + "/" + filename;
            try {
                FileOutputStream fout = new FileOutputStream(pathToImage);
                fout.write(imageDecoded);
                fout.close();
            }catch (FileNotFoundException e) {
                e.printStackTrace();
            }catch (IOException e) {
                e.printStackTrace();
            }
            UserPhotos userPhotos = new UserPhotos();
            userPhotos.setUser(user);
            userPhotos.setCreated_date(new Date());
            userPhotos.setCaption(userPhotodto.getCaption());
            userPhotos.setImage_path(filename);
            photoRepository.save(userPhotos);
        }
    }

//    public List<UserPhotos> getListOfPhotos(List<User> listOfFollowedUsers, Pageable pageable) {
//        final String SQL_QUERY="SELECT u from UserPhotos u where u.user.id=:id";
//        Query query = entityManager.createQuery(SQL_QUERY,UserPhotos.class);
//        List<UserPhotos> userPhotosList = new ArrayList<UserPhotos>();
//        for (User user:listOfFollowedUsers){
//            long id = user.getId();
//            System.out.println(id);
//            query.setParameter("id",id).getResultList();
//            int totalItems =query.getResultList().size();
//
//            query.setFirstResult((pageable.getPageNumber()-1)*pageable.getPageSize());
//            query.setMaxResults(pageable.getPageSize());
//            List <UserPhotos> userPhotosList1 = query.getResultList();
//            List<UserPhotos> list = photoRepository.getUserPhotosByUser_Id(id);
//            for(UserPhotos userPhotos:userPhotosList1) {
//                userPhotos.setTotalItems(totalItems);
//                userPhotosList.add(userPhotos);
//            }
//        }
//        return userPhotosList;
//    }

    public List<UserPostDto> getPosts(String userName, Pageable pageable) {
        List<User> listOfFollowedUsers = followService.getFollowedUsers(userName);
        List<UserPhotodto> userPhotosList = new ArrayList<UserPhotodto>();
        final String SQL_QUERY =
                "SELECT u.username,p.profile_pic,t.user_id,t.image_path,t.created_date,t.caption,f.following_userId FROM photo_table t " +
                        "LEFT JOIN user_table u ON t.user_id = u.id " +
                        "LEFT JOIN follow f ON  u.id = f.following_userId " +
                        "LEFT JOIN profile_pic_table p ON u.id = p.user_id " +
                        "where f.following_userId=:id ORDER BY t.created_date DESC";

        Query query = entityManager.createNativeQuery(SQL_QUERY);
        query.setFirstResult((pageable.getPageNumber() - 1) * pageable.getPageSize());
        query.setMaxResults(pageable.getPageSize());
        for (User user : listOfFollowedUsers) {
            long id = user.getId();
            System.out.println(id);
            query.setParameter("id", id);
//            query.setFirstResult((pageable.getPageNumber() - 1) * pageable.getPageSize());
//            query.setMaxResults(pageable.getPageSize());
            List<Object[]> allList = query.getResultList();
            int totalItems = query.getResultList().size();
            for(Object[] o :allList){
                System.out.println(o[0].toString());
                System.out.println(o[1].toString());
                System.out.println(o[2].toString());
                System.out.println(o[3].toString());
                System.out.println(o[4].toString());
                System.out.println(o[5].toString());
                System.out.println(o[6].toString());

                userPhotosList.add(PhotoUtils.convertObjectListtoUserPhotosList(o));
            }
        }

//        query.setFirstResult((pageable.getPageNumber() - 1) * pageable.getPageSize());
//        query.setMaxResults(pageable.getPageSize());


        //get LikesCount
//        for (UserPhotos userPhotos : userPhotosList) {
//            List<Likes> likes = likesService.getByPhotoId(userPhotos.getId());
//            userPhotos.setLikes(likes);
//        }

        return UserPhotosPostUtil.convertUserPhotosToUserPostDto(userPhotosList);
    }

    public List<UserPhotodto> getAllPhotos(String username) {
        List<UserPhotos> photoList = photoRepository.getUserPhotosByUserUsername(username);
        List<UserPhotodto> userPhotodto = PhotoUtils.convertUserPhotos(photoList);
        return userPhotodto;
    }

    public UserPhotos getPhotos(String image_path) {
       UserPhotos userPhotos= photoRepository.getUserPhotosByImage_path(image_path);
       return userPhotos;
    }

    public long getPhotoCount(String username) {
        List<UserPhotos> userPhotosList=photoRepository.getUserPhotosByUserUsername(username);
        return userPhotosList.size();
    }
}

