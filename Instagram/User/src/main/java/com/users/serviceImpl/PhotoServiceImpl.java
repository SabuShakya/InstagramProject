package com.users.serviceImpl;
import com.users.dto.UserPhotodto;
import com.users.dto.UserPostDto;
import com.users.model.User;
import com.users.model.UserPhotos;
import com.users.repository.LikesRepository;
import com.users.repository.PhotoRepository;
import com.users.repository.UserRepository;
import com.users.service.FollowService;
import com.users.service.LikesService;
import com.users.service.PhotoService;
import com.users.service.UserService;
import com.users.utils.PhotoUtils;
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
    private UserRepository userRepository;

    @Autowired
    private FollowService followService;

    @Autowired
    private LikesRepository likesRepository;

    @Autowired
    private LikesService likesService;

    @Autowired
    private EntityManager entityManager;

    public void savePhoto(UserPhotodto userPhotodto) {
        File dir = new File(System.getProperty("catalina.home") + "/uploads");
        if (!dir.exists()) {
            dir.mkdir();
        }
        User user = userService.getUser(userPhotodto.getUsername());
        for (String s : userPhotodto.getImageList()) {
            byte[] imageDecoded = Base64.getDecoder().decode(s);
            String filename = imageDecoded.toString();
            String pathToImage = dir + "/" + filename;
            try {
                FileOutputStream fout = new FileOutputStream(pathToImage);
                fout.write(imageDecoded);
                fout.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
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

    @Override
//    public List<UserPostDto> getPosts(String userName, Pageable pageable) {
//        User user = userRepository.getUserByUsername(userName);
//        final String SQL_QUERY =
//                "SELECT u.username,t2.profile_pic,t.image_path,t.created_date,t.caption,f.following_userId " +
//                        "FROM photo_table t " +
//                        "LEFT JOIN user_table u ON t.user_id = u.id " +
//                        "LEFT JOIN follow f ON u.id = f.following_userId " +
//                        "LEFT JOIN profile_pic_table t2 ON u.id = t2.user_id " +
//                        "where f.userId=:id ORDER BY t.created_date DESC";
//
//        Query query = entityManager.createNativeQuery(SQL_QUERY).setParameter("id",user.getId());
//        int totalItems = query.getResultList().size();
//        query.setFirstResult((pageable.getPageNumber() - 1) * pageable.getPageSize());
//        query.setMaxResults(pageable.getPageSize());
//
//        List<Object[]> allList = query.getResultList();
//        List<UserPhotodto> userPhotosList = new ArrayList<UserPhotodto>();
//
//        for (Object[] o :allList){
//            int likesCount=(likesRepository.getByUserPhotos_Image_path(o[2].toString())).size();
//            UserPhotodto userPhotodto= PhotoUtils.convertObjectToUserPhotos(o,likesCount);
//            userPhotosList.add(userPhotodto);
//        }
//
//        for (UserPhotodto userPhotos : userPhotosList) {
//            List<Likes> likesList =likesRepository.getByUserPhotos_Image_path(userPhotos.getImage_path());
//            userPhotos.setLikes(likesList);
//            userPhotosList.add(userPhotos);
//            UserPhotosPostUtil.convertUserPhotosToUserPostDto(userPhotosList,totalItems);
//        }
////
//        return UserPhotosPostUtil.convertUserPhotosToUserPostDto(userPhotosList,totalItems);
//
//    }

    public List<UserPostDto> getPosts(String userName, Pageable pageable) {
        User user = userRepository.getUserByUsername(userName);
        final String SQL_QUERY =
                "SELECT u.username,t2.profile_pic,t.image_path,t.created_date,t.caption,f.following_userId " +
                        "FROM photo_table t " +
                        "LEFT JOIN user_table u ON t.user_id = u.id " +
                        "LEFT JOIN follow f ON u.id = f.following_userId " +
                        "LEFT JOIN profile_pic_table t2 ON u.id = t2.user_id " +
                        "where f.userId=:id ORDER BY t.created_date DESC";

        Query query = entityManager.createNativeQuery(SQL_QUERY).setParameter("id",user.getId());
        int totalItems = query.getResultList().size();
        query.setFirstResult((pageable.getPageNumber() - 1) * pageable.getPageSize());
        query.setMaxResults(pageable.getPageSize());

        List<Object[]> allList = query.getResultList();
        List<UserPostDto> userPostDtoList = new ArrayList<UserPostDto>();

        for (Object[] o :allList){
            int likesCount=likesService.getLikesCountForImage(o[2].toString());
            UserPostDto userPostDto= PhotoUtils.convertObjectToUserPhotos(o,likesCount,totalItems);
            userPostDtoList.add(userPostDto);
        }
        return userPostDtoList;
    }

    public List<UserPhotodto> getAllPhotos(String username) {
        List<UserPhotos> photoList = photoRepository.getUserPhotosByUserUsername(username);
        List<UserPhotodto> userPhotodto = PhotoUtils.convertUserPhotos(photoList);
        return userPhotodto;
    }

    public UserPhotos getPhotos(String image_path) {
        UserPhotos userPhotos = photoRepository.getUserPhotosByImage_path(image_path);
        return userPhotos;
    }

    public long getPhotoCount(String username) {
        List<UserPhotos> userPhotosList = photoRepository.getUserPhotosByUserUsername(username);
        return userPhotosList.size();
    }
}

