package com.users.utils;

import com.users.dto.UserPhotodto;
import com.users.dto.UserPostDto;
import com.users.model.UserPhotos;

import java.util.ArrayList;
import java.util.List;

public class PhotoUtils {
    public static UserPostDto convertObjectToUserPhotos(Object[] object, int likesCount, int totalItems) {
        UserPostDto userPostDto = new UserPostDto();
        userPostDto.setUsername(object[0].toString());
        userPostDto.setProfilePic(object[1].toString());
        userPostDto.setImage_path(object[2].toString());
        userPostDto.setCreated_date(object[3].toString());
        userPostDto.setCaption(object[4].toString());
        userPostDto.setActivationStatus(object[6].toString());
        userPostDto.setCountOfLikes(likesCount);
         userPostDto.setTotalItems(totalItems);
        return userPostDto;
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
