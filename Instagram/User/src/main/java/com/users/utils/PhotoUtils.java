package com.users.utils;

import com.users.dto.LikeActiondto;
import com.users.dto.UserPhotodto;
import com.users.dto.UserPostDto;
import com.users.model.UserPhotos;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PhotoUtils {
    public static UserPostDto convertObjectToUserPhotos(Object[] object, LikeActiondto likeActiondto, int totalItems) {
        UserPostDto userPostDto = new UserPostDto();
        userPostDto.setUsername(object[0].toString());
        userPostDto.setProfilePic(object[1].toString());
        userPostDto.setImage_path(object[2].toString());
        SimpleDateFormat dateFormatter = new SimpleDateFormat("MMMM d, yyyy 'at' h:m:s a");
//        System.out.println(dateFormatter.format(object[3].toString()));
//        Date date = DateUtils.getDateWithTimeAndAmPm((Date) object[3]);
        userPostDto.setCreated_date(object[3].toString());
        userPostDto.setCaption(object[4].toString());
        userPostDto.setActivationStatus(object[6].toString());
        userPostDto.setCountOfLikes(likeActiondto.getLikeCount());
        userPostDto.setShowRedButton(likeActiondto.getShowRedButton());
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
