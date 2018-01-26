package com.users.service;

import com.users.dto.UserPhotodto;
import com.users.model.User;
import com.users.model.UserPhotos;
import org.springframework.data.domain.Page;

import java.awt.print.Pageable;
import java.util.ArrayList;
import java.util.List;

public interface PhotoService {
    public void savePhoto(UserPhotodto userPhotos);
    public List<UserPhotos> getListOfPhotos(List<User> listOfFollowedUsers, org.springframework.data.domain.Pageable pageable);
    public List<UserPhotodto> getAllPhotos(String username);
    public UserPhotos getPhotos(String image_path);
    public long getPhotoCount(String username);
    public void deletePhoto(UserPhotodto userPhotodto);
}
