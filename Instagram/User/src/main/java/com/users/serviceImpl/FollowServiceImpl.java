package com.users.serviceImpl;

import com.users.dto.FollowDto;
import com.users.dto.UserPostDto;
import com.users.model.Follow;
import com.users.model.User;
import com.users.model.UserPhotos;
import com.users.repository.FollowRepository;
import com.users.repository.UserRepository;
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
    private UserRepository userRepository;

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

    public void saveFollows(FollowDto followDto) {
        User user = userRepository.getUserByUsername(followDto.getUserName());
        User following_user = userRepository.getUserByUsername(followDto.getFollowing_userName());

        Follow follow = new Follow();
        follow.setUser(user);
        follow.setFollowedUser(following_user);
        follow.setIsFollowing(true);
        followRepository.save(follow);
    }

    public boolean checkFollow(FollowDto followDto) {
        Follow follow =followRepository.checkFollow(followDto.getUserName(),
                                                    followDto.getFollowing_userName());
        if(follow != null){
            return true;
        }
        return false;
    }

    public void unfollowUser(FollowDto followDto) {
      Follow follow=followRepository.checkFollow(followDto.getUserName(),
                                     followDto.getFollowing_userName());
      if (follow !=null) {
          followRepository.delete(follow);
      }
    }
}
