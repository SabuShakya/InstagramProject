package com.f1soft.admin.utils;

import com.f1soft.admin.dto.UserSearchDto;
import com.f1soft.admin.model.User;
import java.util.ArrayList;
import java.util.List;

public class UserSearchUtils {
    public static List<UserSearchDto> getSearchedUserInfo(List<User> userList){
        List<UserSearchDto> userSearchDtoList = new ArrayList<UserSearchDto>();
        for (User user:userList){
            UserSearchDto userSearchDto = new UserSearchDto();
            userSearchDto.setFullname(user.getFullName());
            userSearchDto.setUserId(user.getId());
            userSearchDto.setUsername(user.getUsername());
            userSearchDto.setImagename(user.getProfilePhotos().getProfile_pic());
            userSearchDto.setActivationStatus(user.getUserActivation().getActivationStatus());
            userSearchDto.setShowResultButtons(true);
            userSearchDto.setHideButtons(false);
            userSearchDtoList.add(userSearchDto);
        }
        return userSearchDtoList;
    }
}
