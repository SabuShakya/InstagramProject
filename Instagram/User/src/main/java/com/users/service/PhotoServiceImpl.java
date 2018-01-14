package com.users.service;
import com.sun.org.apache.xpath.internal.operations.Mod;
import com.users.dto.UserPhotodto;
import com.users.model.User;
import com.users.model.UserPhotos;
import com.users.repository.PhotoRepository;
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
        File dir = new File(System.getProperty("catalina.home")+ "/upload");
        if(!dir.exists()){
            dir.mkdir();
        }
            byte[] imageDecoded = Base64.getDecoder().decode(userPhotodto.getImage_path());
            String filename = imageDecoded + ".jpg";
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
            photoRepository.save(userPhotos);
    }

    public List<UserPhotodto> getAllPhotos() {
        List<UserPhotos> photoList = photoRepository.findAll();
        ModelMapper modelMapper = new ModelMapper();
        java.lang.reflect.Type targetListType = new TypeToken<List<UserPhotodto>>() {}.getType();
        List<UserPhotodto> userPhotodtoList = modelMapper.map(photoList, targetListType);
        return userPhotodtoList;
    }
}
