package com.users.utils;

import com.users.dto.UserPhotodto;
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
            userPhotodto.setCreated_date(userPhotos.getCreated_date());
            userPhotodto.setCaption(userPhotos.getCaption());
            userPhotodtoList.add(userPhotodto);
        }
        return userPhotodtoList;
    }
}
