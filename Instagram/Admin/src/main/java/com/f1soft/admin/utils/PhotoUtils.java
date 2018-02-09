package com.f1soft.admin.utils;


import com.f1soft.admin.dto.UserPhotodto;
import com.f1soft.admin.model.UserPhotos;

import java.util.ArrayList;
import java.util.List;

public class PhotoUtils {
    public static UserPhotodto convertObjectToUserPhotos(Object[] object) {
        UserPhotodto userPhotodto = new UserPhotodto();
        userPhotodto.setUsername(object[0].toString());
        userPhotodto.setProfile_pic(object[1].toString());
        userPhotodto.setImage_path(object[2].toString());
        userPhotodto.setCreated_date(object[3].toString());
        userPhotodto.setCaption(object[4].toString());
//        userPostDto.setCountOfLikes(likesCount);
//        userPostDto.setTotalItems(totalItems);
        return userPhotodto;
    }

    public static List<UserPhotodto> convertUserPhotos(List<UserPhotos> userPhotodtos) {
        List<UserPhotodto> userPhotodtoList = new ArrayList<UserPhotodto>();
        for (UserPhotos userPhotos : userPhotodtos) {
            UserPhotodto userPhotodto = new UserPhotodto();
            userPhotodto.setUsername(userPhotos.getUser().getUsername());
            userPhotodto.setImage_path(userPhotos.getImage_path());
            userPhotodto.setCreated_date(userPhotos.getCreated_date().toString());
            userPhotodto.setCaption(userPhotos.getCaption());
            userPhotodtoList.add(userPhotodto);
        }
        return userPhotodtoList;
    }
}
