package com.users.service;

import com.users.dto.UserPhotodto;
import com.users.model.User;
import com.users.model.UserPhotos;

import java.util.ArrayList;
import java.util.List;

public interface PhotoService {
    public void savePhoto(UserPhotodto userPhotos);
    public List<UserPhotos> getListOfPhotos(List<User> listOfFollowedUsers);
    public List<UserPhotodto> getAllPhotos(String username);
    public UserPhotos getPhotos(String image_path);
    public long getPhotoCount(String username);
}
