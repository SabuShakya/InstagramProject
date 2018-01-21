package com.users.serviceImpl;
import com.sun.org.apache.xpath.internal.operations.Mod;
import com.users.dto.UserPhotodto;
import com.users.model.User;
import com.users.model.UserPhotos;
import com.users.repository.PhotoRepository;
import com.users.service.PhotoService;
import com.users.service.UserService;
import com.users.utils.PhotoUtils;
import com.users.utils.TokenUtils;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
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

    public void savePhoto(UserPhotodto userPhotodto){
        File dir = new File(System.getProperty("catalina.home")+ "/uploads");
        if(!dir.exists()){
            dir.mkdir();
        }
            byte[] imageDecoded = Base64.getDecoder().decode(userPhotodto.getImage_path());
            String filename = imageDecoded.toString();
//                    + ".jpg";
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
            User user = userService.getUser(userPhotodto.getUsername());
            UserPhotos userPhotos = new UserPhotos();
            userPhotos.setUser(user);
            userPhotos.setCreated_date(new Date());
            userPhotos.setCaption(userPhotodto.getCaption());
            userPhotos.setImage_path(filename);
//            userPhotos.setProfileImg(userPhotodto.getProfileImg());
            photoRepository.save(userPhotos);
    }

//sabu
    public List<UserPhotos> getListOfPhotos(List<User> listOfFollowedUsers) {
        List<UserPhotos> userPhotosList = new ArrayList<UserPhotos>();
        for (User user:listOfFollowedUsers){
            long id = user.getId();
            System.out.println(id);
            List<UserPhotos> list = photoRepository.getUserPhotosByUser_Id(id);
            for(UserPhotos userPhotos:list) {
                userPhotosList.add(userPhotos);
            }
        }
        return userPhotosList;
    }

    public List<UserPhotodto> getAllPhotos(String username) {
        List<UserPhotos> photoList = photoRepository.getUserPhotosByUserUsername(username);
        List<UserPhotodto> userPhotodto = PhotoUtils.convertUserPhotos(photoList);
        return userPhotodto;
//        ModelMapper modelMapper = new ModelMapper();
//        java.lang.reflect.Type targetListType = new TypeToken<List<UserPhotodto>>() {}.getType();
//        List<UserPhotodto> userPhotodtoList = modelMapper.map(photoList, targetListType);
    }

    public UserPhotos getPhotos(String image_path) {
       UserPhotos userPhotos= photoRepository.getUserPhotosByImage_path(image_path);
       return userPhotos;
    }

    @Override
    public long getPhotoCount(String username) {
        List<UserPhotos> userPhotosList=photoRepository.getUserPhotosByUserUsername(username);
        return userPhotosList.size();
    }
//
//    public void updateProfile(UserPhotodto userPhotodto) {
//        File dir = new File(System.getProperty("catalina.home")+ "/uploads");
//        if(!dir.exists()){
//            dir.mkdir();
//        }
//        byte[] imageDecoded = Base64.getDecoder().decode(userPhotodto.getImage_path());
//        String filename = imageDecoded.toString();
////                    + ".jpg";
//        String pathToImage = dir + "/" + filename;
//        try {
//            FileOutputStream fout = new FileOutputStream(pathToImage);
//            fout.write(imageDecoded);
//            fout.close();
//        }catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }catch (IOException e) {
//            e.printStackTrace();
//        }
//        User user = userService.getUser(userPhotodto.getUsername());
//        UserPhotos userPhotos = new UserPhotos();
//        userPhotos.setUser(user);
//        userPhotos.setProfileImg(filename);
//        photoRepository.save(userPhotos);
//    }
}
