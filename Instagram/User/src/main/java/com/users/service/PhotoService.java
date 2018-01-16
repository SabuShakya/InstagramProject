package com.users.service;

import com.users.dto.UserPhotodto;
import com.users.model.User;
import com.users.model.UserPhotos;

import java.util.ArrayList;
import java.util.List;

public interface PhotoService {
    public void savePhoto(UserPhotodto userPhotos);
    public List<UserPhotodto> getAllPhotos();
    public List<UserPhotos> getListOfPhotos(List<User> listOfFollowedUsers);
}
