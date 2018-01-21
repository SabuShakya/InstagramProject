package com.users.serviceImpl;

import com.users.dto.ProfilePhotoDto;
import com.users.model.ProfilePhoto;
import com.users.model.User;
import com.users.repository.ProfilePhotoRepository;
import com.users.service.ProfilePhotoService;
import com.users.service.UserService;
import com.users.utils.ProfilePhotoUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.List;

@Service
@Transactional
public class ProfilePhotoServiceImpl implements ProfilePhotoService{

    @Autowired
    private ProfilePhotoRepository profilePhotoRepository;

    @Autowired
    private UserService userService;

    @Autowired
    public EntityManager em;

    public void savePhoto(ProfilePhotoDto profilePhotoDto){
        File dir = new File(System.getProperty("catalina.home")+ "/uploads");
        if(!dir.exists()){
            dir.mkdir();
        }
        byte[] imageDecoded = Base64.getDecoder().decode(profilePhotoDto.getProfile_pic());
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
        User user = userService.getUser(profilePhotoDto.getUsername());
        ProfilePhoto profilePhoto = new ProfilePhoto();
        profilePhoto.setUser(user);
        profilePhoto.setProfile_pic(filename);
        profilePhotoRepository.save(profilePhoto);
    }

//    public ProfilePhotoDto getProfilePhoto(String username){
//        ProfilePhoto profilePhoto= profilePhotoRepository.getProfilePhotoByUserUsername(username);
//        ProfilePhotoDto profilePhotoDto= new ProfilePhotoDto();
//        profilePhotoDto.setProfile_pic(profilePhoto.getProfile_pic());
//        profilePhotoDto.setUsername(profilePhoto.getUser().getUsername());
//       return profilePhotoDto;
//    }

    public ProfilePhotoDto getProfilePhoto(String username){
        String sql = "Select p from ProfilePhoto p where p.user.username like :username";
        ProfilePhoto profilePhoto =em.createQuery(sql,ProfilePhoto.class).setParameter
                ("username","%"+username+"%").getSingleResult();
        ProfilePhotoDto profilePhotoDto= new ProfilePhotoDto();
        profilePhotoDto.setProfile_pic(profilePhoto.getProfile_pic());
        profilePhotoDto.setUsername(profilePhoto.getUser().getUsername());
        return profilePhotoDto;
    }
}
