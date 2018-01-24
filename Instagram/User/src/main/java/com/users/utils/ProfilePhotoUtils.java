package com.users.utils;

import com.users.dto.ProfilePhotoDto;
import com.users.model.ProfilePhoto;

import java.util.ArrayList;
import java.util.List;

public class ProfilePhotoUtils {
    public static List<ProfilePhotoDto> convertProfilePhotodto(List<ProfilePhoto> profilePhotoDtos) {
        List<ProfilePhotoDto> profilePhotoDtoList = new ArrayList<ProfilePhotoDto>();
        for (ProfilePhoto profilePhoto : profilePhotoDtos) {
            ProfilePhotoDto profilePhotoDto = new ProfilePhotoDto();
            profilePhotoDto.setUsername(profilePhoto.getUser().getUsername());
            profilePhoto.setProfile_pic(profilePhoto.getProfile_pic());
            profilePhotoDtoList.add(profilePhotoDto);
        }
        return profilePhotoDtoList;
    }
}
