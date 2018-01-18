package com.users.utils;

import com.users.dto.UserPostDto;
import com.users.model.UserPhotos;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class UserPhotosPostUtil {
    public static List<UserPostDto> convertUserPhotosToUserPostDto(List<UserPhotos> userPhotosList){
//        Type targetListType = new TypeToken<List<UserPostDto>>(){}.getType();
//        ModelMapper modelMapper = new ModelMapper();
//        List<UserPostDto> userPostDtoList = modelMapper.map(userPhotosList,targetListType);
//        return userPostDtoList;
        List<UserPostDto> userPostDtoList = new ArrayList<UserPostDto>();
        for (UserPhotos userPhotos:userPhotosList){
            UserPostDto userPostDto = new UserPostDto();
            userPostDto.setUsername(userPhotos.getUser().getUsername());
            userPostDto.setImage_path(userPhotos.getImage_path());
            userPostDto.setCreated_date(userPhotos.getCreated_date());
            userPostDto.setCaption(userPhotos.getCaption());
            userPostDtoList.add(userPostDto);
        }
        return userPostDtoList;
    }
}
