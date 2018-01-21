package com.users.service;

import com.users.dto.ProfilePhotoDto;
import com.users.model.ProfilePhoto;

import java.util.List;

public interface ProfilePhotoService {
    public void savePhoto(ProfilePhotoDto profilePhotoDto);
    public ProfilePhotoDto getProfilePhoto(String username);
}
