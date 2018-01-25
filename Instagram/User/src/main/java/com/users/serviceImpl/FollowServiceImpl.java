package com.users.serviceImpl;

import com.users.dto.FollowCountDto;
import com.users.dto.FollowDto;
import com.users.dto.UserPostDto;
import com.users.model.Follow;
import com.users.model.Likes;
import com.users.model.User;
import com.users.model.UserPhotos;
import com.users.repository.FollowRepository;
import com.users.repository.UserRepository;
import com.users.service.FollowService;
import com.users.service.LikesService;
import com.users.service.PhotoService;
import com.users.utils.FollowUtils;
import com.users.utils.UserPhotosPostUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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

    @Autowired
    private LikesService likesService;

    public List<UserPostDto> getPosts(String username) {
        List<User> listOfFollowedUsers = followRepository.getFollowedUser(username);
        System.out.println(listOfFollowedUsers);
        List<UserPhotos> userPhotosList= photoService.getListOfPhotos(listOfFollowedUsers);
        for (UserPhotos userPhotos: userPhotosList){
            List<Likes> likes = likesService.getByPhotoId(userPhotos.getId());
            userPhotos.setLikes(likes);
        }
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

    @Override
    public FollowCountDto getFollowCount(String username) {
        FollowCountDto followCountDto = new FollowCountDto();
        User user=userRepository.getUserByUsername(username);
        List<Follow> followingList=followRepository.getByUserId(user.getId());
        followCountDto.setFollowing(followingList.size());
        List<User> followersList = followRepository.getByFollowedUserId(user.getId());
        followCountDto.setFollowers(followersList.size());
        return followCountDto;
    }

    public List<FollowDto> getFollowersList(String username) {
        User user = userRepository.getUserByUsername(username);
        List<User> followersList = followRepository.getByFollowedUserId(user.getId());
        return FollowUtils.convertFollowtoFollowingDto(followersList);
    }

    public List<FollowDto> getFollowingList(String username) {
        User user = userRepository.getUserByUsername(username);
        List<User> followingList = followRepository.getByFollowingUserId(user.getId());
        return FollowUtils.convertFollowtoFollowDto(followingList);
    }
}
