package com.f1soft.admin.serviceimpl;

import com.f1soft.admin.dto.FollowCountDto;
import com.f1soft.admin.dto.FollowDto;
import com.f1soft.admin.model.Follow;
import com.f1soft.admin.model.User;
import com.f1soft.admin.repository.CommentsRepository;
import com.f1soft.admin.repository.FollowRepository;
import com.f1soft.admin.repository.UserRepository;
import com.f1soft.admin.service.CommentsService;
import com.f1soft.admin.service.FollowService;

import com.f1soft.admin.service.UserPhotosService;
import com.f1soft.admin.utils.FollowUtils;
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
    private UserPhotosService photoService;

//    @Autowired
//    private LikesService likesService;

    @Autowired
    private CommentsService commentsService;

    @Autowired
    private CommentsRepository commentsRepository;

    public List<User> getFollowedUsers(String username) {
        List<User> listOfFollowedUsers = followRepository.getFollowedUser(username);
        System.out.println(listOfFollowedUsers);
//        List<UserPhotos> userPhotosList= photoService.getListOfPhotos(listOfFollowedUsers,pageable);
//        for (UserPhotos userPhotos: userPhotosList){
//            List<Likes> likes = likesService.getByPhotoId(userPhotos.getId());
//            userPhotos.setLikes(likes);
//        }
//        return UserPhotosPostUtil.convertUserPhotosToUserPostDto(userPhotosList);
        return listOfFollowedUsers;
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
        return FollowUtils.convertFollowtoFollowDto(followersList);
    }

    public List<FollowDto> getFollowingList(String username) {
        User user = userRepository.getUserByUsername(username);
        List<User> followingList = followRepository.getByFollowingUserId(user.getId());
        return FollowUtils.convertFollowtoFollowingDto(followingList);
    }
}
