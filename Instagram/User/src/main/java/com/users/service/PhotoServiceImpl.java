package com.users.service;
import com.users.dto.UserPhotodto;
import com.users.model.UserPhotos;
import com.users.repository.PhotoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

   @Autowired
   private PhotoRepository photoRepository;

    public void savePhoto (UserPhotodto userPhotodto){
        File dir = new File(System.getProperty("catalina.home")+ "/uploads");
        if(!dir.exists()){
            dir.mkdir();
        }
            byte[] imageDecoded = Base64.getDecoder().decode(userPhotodto.getImage_path());
            String filename = imageDecoded + ".jpg";
            String pathToImage = dir + "/" + filename;
            try {
                FileOutputStream fout = new FileOutputStream
                        (pathToImage);
                fout.write(imageDecoded);
                fout.close();
            }catch (FileNotFoundException e) {
                e.printStackTrace();
            }catch (IOException e) {
                e.printStackTrace();
            }
            userPhotodto.setImage_path(filename);

            ModelMapper modelMapper = new ModelMapper();
            UserPhotos userPhotoEntity = modelMapper.map(userPhotodto, UserPhotos.class);
            userPhotoEntity.setCreated_date(new Date());
            photoRepository.save(userPhotoEntity);
    }

    public List<UserPhotos> getAllPhotos() {
        List<UserPhotos> photoList = photoRepository.findAll();
        System.out.println(photoList.toString());
        return photoList;
    }
}
