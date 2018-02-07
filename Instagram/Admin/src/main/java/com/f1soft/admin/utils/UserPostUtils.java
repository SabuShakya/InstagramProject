package com.f1soft.admin.utils;

import com.f1soft.admin.dto.UserPostDto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserPostUtils {
    public static UserPostDto convertToUserPostDto(Object object[],int likesCount,int totalItems) {
        UserPostDto userPostDto = new UserPostDto();
        userPostDto.setUsername(object[0].toString());
        userPostDto.setImage_path(object[1].toString());
        userPostDto.setCreated_date(object[2].toString());
        userPostDto.setCaption(object[3].toString());
        userPostDto.setProfilePic(object[4].toString());
        userPostDto.setCountOfLikes(likesCount);
        userPostDto.setTotalItems(totalItems);
        return userPostDto;
    }
}
