package com.users.serviceImpl;

import com.users.dto.UserPostDto;
import com.users.model.User;
import com.users.model.UserPhotos;
import com.users.repository.FollowRepository;
import com.users.service.FollowService;
import com.users.service.PhotoService;
import com.users.utils.UserPhotosPostUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class FollowServiceImpl implements FollowService {

    @Autowired
    private FollowRepository followRepository;

    @Autowired
    private PhotoService photoService;

    public List<UserPostDto> getPosts(String username) {
        List<User> listOfFollowedUsers = followRepository.getFollowedUser(username);
        System.out.println(listOfFollowedUsers);
        List<UserPhotos> userPhotosList= photoService.getListOfPhotos(listOfFollowedUsers);
        return UserPhotosPostUtil.convertUserPhotosToUserPostDto(userPhotosList);
    }
}
