package com.users.utils;

import com.users.dto.UserPhotodto;
import com.users.dto.UserPostDto;
import com.users.model.UserPhotos;

import java.util.ArrayList;
import java.util.List;

public class PhotoUtils {
    public static List<UserPhotodto> convertUserPhotos(List<UserPhotos> userPhotodtos) {
        List<UserPhotodto> userPhotodtoList = new ArrayList<UserPhotodto>();
        for (UserPhotos userPhotos : userPhotodtos) {
            UserPhotodto userPhotodto = new UserPhotodto();
            userPhotodto.setUsername(userPhotos.getUser().getUsername());
            userPhotodto.setImage_path(userPhotos.getImage_path());
//            userPhotodto.setCreated_date(userPhotos.getCreated_date());
            userPhotodto.setCaption(userPhotos.getCaption());
            userPhotodtoList.add(userPhotodto);
        }
        return userPhotodtoList;
    }

   public static UserPhotodto convertObjectListtoUserPhotosList(Object object[] ){
       UserPhotodto userPhotodto = new UserPhotodto();
       userPhotodto.setUsername(object[0].toString());
       userPhotodto.setProfile_pic(object[1].toString());
//       userPhotodto.setId(object[3]);
       userPhotodto.setImage_path(object[3].toString());
       userPhotodto.setCreated_date(object[4].toString());
       userPhotodto.setCaption(object[5].toString());
       return userPhotodto;
   }
}
