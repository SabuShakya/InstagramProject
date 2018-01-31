package com.f1soft.admin.service;

import com.f1soft.admin.dto.UserPhotodto;
import com.f1soft.admin.model.User;
import com.f1soft.admin.model.UserPhotos;

import java.util.List;

public interface PhotoService {
    public void savePhoto(UserPhotodto userPhotos);
    public List<UserPhotos> getListOfPhotos(List<User> listOfFollowedUsers, org.springframework.data.domain.Pageable pageable);
    public List<UserPhotodto> getAllPhotos(String username);
    public UserPhotos getPhotos(String image_path);
    public long getPhotoCount(String username);
}
