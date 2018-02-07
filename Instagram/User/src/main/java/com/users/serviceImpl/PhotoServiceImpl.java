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
    public List<UserPostDto> getPosts(String userName, Pageable pageable) {

        //getting List of Followed Users
        List<User> listOfFollowedUsers = followService.getFollowedUsers(userName);
        List<UserPhotos> userPhotosList = new ArrayList<UserPhotos>();
        final String SQL_QUERY =
                "SELECT t.image_path,t.created_date,t.caption,f.following_userId FROM photo_table t " +
                        "LEFT JOIN user_table u ON t.user_id = u.id " +
                        "LEFT JOIN follow f ON u.id = f.userId " +
                        "where f.following_userId=:id ORDER BY t.created_date DESC";
        Query query = entityManager.createNativeQuery(SQL_QUERY);

        //getting followed users photo List
        for (User user : listOfFollowedUsers) {
            long id = user.getId();
            System.out.println(id);
            query.setParameter("id", id);
            int totalItems = query.getResultList().size();

            List<UserPhotos> allList = query.getResultList();
            System.out.println(allList.toString());


            //setting total Items
            List<UserPhotos> userPhotosList1 = query.getResultList();
            for (UserPhotos userPhotos : userPhotosList1) {
                userPhotos.setTotalItems(totalItems);
                userPhotosList.add(userPhotos);
            }
        }
        //get LikesCount
        for (UserPhotos userPhotos : userPhotosList) {
            List<Likes> likes = likesService.getByPhotoId(userPhotos.getId());
            userPhotos.setLikes(likes);
        }

        query.setFirstResult((pageable.getPageNumber() - 1) * pageable.getPageSize());
        query.setMaxResults(pageable.getPageSize());

        return UserPhotosPostUtil.convertUserPhotosToUserPostDto(userPhotosList);

    }

//    public List<UserPhotos> getListOfPhotos(String userName, Pageable pageable) {
//
//    }

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

