package com.f1soft.admin.utils;

import com.f1soft.admin.dto.UserListInfoDto;
import com.f1soft.admin.model.User;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;

public class TotalUsersUtil {

        public static List<UserListInfoDto> convertUsersListToUserListInfoDto(List<User> list){
        List<UserListInfoDto> userListInfoDtoList = new ArrayList<UserListInfoDto>();
        for (User user: list){
            UserListInfoDto userListInfoDto = new UserListInfoDto();
            userListInfoDto.setFullname(user.getFullName());
            userListInfoDto.setUserId(user.getId());
            userListInfoDto.setUsername(user.getUsername());
            userListInfoDto.setImagename(user.getProfilePhotos().getProfile_pic());
            userListInfoDto.setTotalItems(user.getTotalItems());
            userListInfoDtoList.add(userListInfoDto);
        }
        return userListInfoDtoList;
    }

}
