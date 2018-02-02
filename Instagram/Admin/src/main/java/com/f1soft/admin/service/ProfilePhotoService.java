package com.f1soft.admin.service;

import com.f1soft.admin.dto.ProfilePhotoDto;

public interface ProfilePhotoService {
    public void savePhoto(ProfilePhotoDto profilePhotoDto);
    public ProfilePhotoDto getProfilePhoto(String username);

}
