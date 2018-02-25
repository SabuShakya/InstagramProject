package com.f1soft.admin.serviceimpl;

import com.f1soft.admin.dto.*;
import com.f1soft.admin.model.Comments;
import com.f1soft.admin.model.ProfilePhoto;
import com.f1soft.admin.model.User;
import com.f1soft.admin.model.UserPhotos;
import com.f1soft.admin.repository.*;
import com.f1soft.admin.service.UserPhotosService;
import com.f1soft.admin.utils.DateUtils;
import com.f1soft.admin.utils.PhotoUtils;
import com.f1soft.admin.utils.UserPostUtils;
import jdk.nashorn.internal.objects.annotations.Constructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class UserPhotosServiceImpl implements UserPhotosService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PhotoRepository photoRepository;

    @Autowired
    private ProfilePhotoRepository profilePhotoRepository;

    @Autowired
    private LikesRepository likesRepository;

    @Autowired
    private CommentsRepository commentsRepository;

    @Autowired
    private EntityManager entityManager;

    public List<UsersTotalUploadsDto> getUsersUploadsCount() {
        List<User> users = userRepository.findAll();
        List<UsersTotalUploadsDto> resultList = new ArrayList<UsersTotalUploadsDto>();
        for (User user : users) {
            List<UserPhotos> photos = photoRepository.getUserPhotosByUser_Id(user.getId());
            ProfilePhoto profilePhoto = profilePhotoRepository.getProfilePhotoByUserUsername(user.getUsername());
            UsersTotalUploadsDto usersTotalUploads = new UsersTotalUploadsDto();
            usersTotalUploads.setUserName(user.getUsername());
            usersTotalUploads.setFullName(user.getFullName());
            usersTotalUploads.setProfilePhoto(profilePhoto.getProfile_pic());
            usersTotalUploads.setTotalUploads(photos.size());
            resultList.add(usersTotalUploads);
        }
        return resultList;
    }

    @Override
    public List<UserPostDto> getUserUploads(String userName, Pageable pageable) {

        List<UserPostDto> resultList = new ArrayList<UserPostDto>();

        String sql = "SELECT u.username,pt.image_path, pt.created_date, pt.caption," +
                " t2.profile_pic " +
                "FROM user_table u" +
                " LEFT JOIN photo_table pt ON u.id = pt.user_id" +
                " LEFT JOIN profile_pic_table t2 ON u.id = t2.user_id" +
                " WHERE u.username = :userName";

        Query query = entityManager.createNativeQuery(sql).setParameter("userName", userName);
        int totalItems = query.getResultList().size();
        query.setFirstResult((pageable.getPageNumber() - 1) * pageable.getPageSize());
        query.setMaxResults(pageable.getPageSize());
        List<Object[]> list = query.getResultList();

        for (Object[] o : list) {
            System.out.println(o[0].toString());
            System.out.println(o[1].toString());
            System.out.println(o[2].toString());
            System.out.println(o[3].toString());
            System.out.println(o[4].toString());
//            getting likesCount
            int likesCount = (likesRepository.getByUserPhotos_Image_path(o[1].toString())).size();
            List<UploadsCommentDto> listOfCommentObject = getComments(o[1].toString());
            resultList.add(UserPostUtils.convertToUserPostDto(o, likesCount, totalItems,listOfCommentObject));
        }
        return resultList;
    }

    @Override
    public List<UserPostDto> getUploadsPerDay() {
        List<UserPostDto> resultList = new ArrayList<UserPostDto>();
        String sql = "SELECT u.username,pt.image_path, pt.created_date, pt.caption," +
                " t2.profile_pic " +
                "FROM user_table u" +
                " LEFT JOIN photo_table pt ON u.id = pt.user_id" +
                " LEFT JOIN profile_pic_table t2 ON u.id = t2.user_id" +
                " WHERE pt.created_date >:date";
//        LocalDate today = LocalDate.now();
        Date today = DateUtils.getTodaysDateWithTimeSetToZero();
        Query query = entityManager.createNativeQuery(sql).setParameter("date", today);
        List<Object[]> list = query.getResultList();
        for (Object[] o : list) {
            System.out.println(o[0].toString());
            System.out.println(o[1].toString());
            System.out.println(o[2].toString());
            System.out.println(o[3].toString());
            System.out.println(o[4].toString());
//            getting likesCount
            int likesCount = (likesRepository.getByUserPhotos_Image_path(o[1].toString())).size();
            List<UploadsCommentDto> listOfCommentObject = getComments(o[1].toString());
            resultList.add(UserPostUtils.convertToUserPostDto(o, likesCount, 0,listOfCommentObject));
        }
        return resultList;
    }

    public List<UserPhotodto> getAllPhotos(String username) {
        List<UserPhotos> photoList = photoRepository.getUserPhotosByUserUsername(username);
        List<UserPhotodto> userPhotodto = PhotoUtils.convertUserPhotos(photoList);
        return userPhotodto;
    }

    public ProfilePhotoDto getProfilePhoto(String username) {
        ProfilePhoto profilePhoto = profilePhotoRepository.getProfilePhotoByUserUsername(username);
        ProfilePhotoDto profilePhotoDto =  new ProfilePhotoDto();
        profilePhotoDto.setProfile_pic(profilePhoto.getProfile_pic());
        profilePhotoDto.setUsername(profilePhoto.getUser().getUsername());
        return profilePhotoDto;
    }

    public long getPhotoCount(String username) {
        List<UserPhotos> userPhotosList = photoRepository.getUserPhotosByUserUsername(username);
        return userPhotosList.size();
    }

    public List<UploadsCommentDto> getComments(String imagePath){
        String commentSql ="SELECT c.comments,u.username FROM comment_table c LEFT JOIN photo_table table2 ON c.pic_id = table2.id LEFT JOIN user_table u ON c.user_id = u.id WHERE table2.image_path= :imagePath";
        Query commentQuery = entityManager.createNativeQuery(commentSql).setParameter("imagePath",imagePath);
        List<Object[]> listOfCommentObject =commentQuery.getResultList();
        List<UploadsCommentDto> commentsList = new ArrayList<UploadsCommentDto>();
        for (Object[] oo : listOfCommentObject) {
            UploadsCommentDto uploadsCommentDto = new UploadsCommentDto();
            uploadsCommentDto.setUserName(oo[1].toString());
            uploadsCommentDto.setComments(oo[0].toString());
            commentsList.add(uploadsCommentDto);
        }
            return commentsList;
    }
}
