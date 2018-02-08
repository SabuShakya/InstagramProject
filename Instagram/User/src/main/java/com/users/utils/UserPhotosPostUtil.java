package com.users.utils;

import com.users.dto.Likesdto;
import com.users.dto.UserPhotodto;
import com.users.dto.UserPostDto;
import com.users.model.UserPhotos;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

//public class UserPhotosPostUtil {
//    public static List<UserPostDto> convertUserPhotosToUserPostDto(List<UserPhotos> userPhotosList){
////        Type targetListType = new TypeToken<List<UserPostDto>>(){}.getType();
////        ModelMapper modelMapper = new ModelMapper();
////        List<UserPostDto> userPostDtoList = modelMapper.map(userPhotosList,targetListType);
////        return userPostDtoList;
//        List<UserPostDto> userPostDtoList = new ArrayList<UserPostDto>();
//
//        for (UserPhotos userPhotos:userPhotosList){
//            int countofLikes=0;
//            UserPostDto userPostDto = new UserPostDto();
//            userPostDto.setUsername(userPhotos.getUser().getUsername());
//            userPostDto.setImage_path(userPhotos.getImage_path());
//            userPostDto.setCreated_date(userPhotos.getCreated_date().toString());
//            userPostDto.setCaption(userPhotos.getCaption());
//
//            List<Likesdto> likesdtoListt=LikesUtil.convertLikesToLikesDto(userPhotos.getLikes());
//            for (Likesdto likesdto:likesdtoListt){
//                if(likesdto.getActivationStatus().equals("activated")) {
//                    countofLikes = countofLikes+1;
//                }
//            }
////           userPhotos.setLikes(likes);
////            userPostDto.setCountOfLikes(userPhotos.getLikes().size());
//            userPostDto.setCountOfLikes(countofLikes);
//
//            userPostDto.setTotalItems(userPhotos.getTotalItems());
//            userPostDto.setProfilePic(userPhotos.getUser().getProfilePhotos().getProfile_pic());
//            userPostDtoList.add(userPostDto);
//        }
//        return userPostDtoList;
//    }
//}
//

public class UserPhotosPostUtil {
    public static List<UserPostDto> convertUserPhotosToUserPostDto(List<UserPhotodto> userPhotosList){
        List<UserPostDto> userPostDtoList = new ArrayList<UserPostDto>();

        for (UserPhotodto userPhotos:userPhotosList){
            int countofLikes=0;
            UserPostDto userPostDto = new UserPostDto();
            userPostDto.setUsername(userPhotos.getUsername());
            userPostDto.setProfilePic(userPhotos.getProfile_pic());
            userPostDto.setImage_path(userPhotos.getImage_path());
            userPostDto.setCaption(userPhotos.getCaption());
            userPostDto.setCreated_date(userPhotos.getCreated_date().toString());
            userPostDto.setTotalItems(userPhotos.getTotalItems());

//            List<Likesdto> likesdtoListt=LikesUtil.convertLikesToLikesDto(userPhotos.getLikes());
//            for (Likesdto likesdto:likesdtoListt){
//                if(likesdto.getActivationStatus().equals("activated")) {
//                    countofLikes = countofLikes+1;
//                }
//            }
//           userPhotos.setLikes(likes);
//            userPostDto.setCountOfLikes(userPhotos.getLikes().size());
            userPostDto.setCountOfLikes(countofLikes);
            userPostDto.setTotalItems(userPhotos.getTotalItems());
            userPostDtoList.add(userPostDto);
        }
        return userPostDtoList;
    }
}


