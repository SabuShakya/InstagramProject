package com.f1soft.admin.utils;

import com.f1soft.admin.dto.FollowDto;
import com.f1soft.admin.model.User;

import java.util.ArrayList;
import java.util.List;

public class FollowUtils {
    public static List<FollowDto> convertFollowtoFollowingDto(List<User> userList){
        List<FollowDto> followDtoList = new ArrayList<FollowDto>();
        for (User user:userList){
            FollowDto followDto =  new FollowDto();
            followDto.setUserName(user.getUsername());
//            followDto.setFollowing_userName(user.getFullName());
            followDto.setImage(user.getProfilePhotos().getProfile_pic());
            followDtoList.add(followDto);
        }
        return followDtoList;
    }

    public static List<FollowDto> convertFollowtoFollowDto(List<User> userList){
        List<FollowDto> followDtoList = new ArrayList<FollowDto>();
        for (User user:userList){
            FollowDto followDto =  new FollowDto();
            followDto.setFollowing_userName(user.getUsername());
            followDto.setImage(user.getProfilePhotos().getProfile_pic());
            followDtoList.add(followDto);
        }
        return followDtoList;
    }
}

