package com.users.serviceImpl;

import com.users.dto.*;
import com.users.model.*;
import com.users.repository.CommentsRepository;
import com.users.repository.FollowRepository;
import com.users.repository.UserRepository;
import com.users.service.*;
import com.users.utils.CommentUtils;
import com.users.utils.FollowUtils;
//import com.users.utils.UserPhotosPostUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    @Autowired
    private CommentsService commentsService;

    @Autowired
    private CommentsRepository commentsRepository;

    public List<User> getFollowedUsers(String username) {
        List<User> listOfFollowedUsers = followRepository.getFollowedUser(username);
        System.out.println(listOfFollowedUsers);
        return listOfFollowedUsers;
    }

    public void saveFollows(FollowDto followDto) {
        User user = userRepository.getUserByUsername(followDto.getUserName());
        User following_user = userRepository.getUserByUsername(followDto.getFollowing_userName());
        Follow follow = followRepository.findByUser_IdAndAndFollowedUser_Id(user.getId(), following_user.getId());
        if (follow == null) {
            follow = new Follow();
            follow.setUser(user);
            follow.setFollowedUser(following_user);
            follow.setIsFollowing(true);
            followRepository.save(follow);
        } else {
            follow.setIsFollowing(true);
            followRepository.save(follow);
        }
    }

    public boolean checkFollow(FollowDto followDto) {
        Follow follow = followRepository.checkFollow(followDto.getUserName(),
                followDto.getFollowing_userName());
        if (follow != null) {
            if (follow.getIsFollowing()) {
                return true;
            }
            return false;
        }
        return false;
    }

    public void unfollowUser(FollowDto followDto) {
        Follow follow = followRepository.checkFollow(followDto.getUserName(),
                followDto.getFollowing_userName());
        if (follow != null) {
            follow.setIsFollowing(false);
            followRepository.save(follow);
//          followRepository.delete(follow);
        }
    }

    public FollowCountDto getFollowCount(String username) {
        FollowCountDto followCountDto = new FollowCountDto();
        User user = userRepository.getUserByUsername(username);
        List<Follow> followingList = followRepository.getByUserId(user.getId());
        List<Follow> resultList = new ArrayList<Follow>();
        for (Follow f : followingList) {
            if (f.getFollowedUser().getUserActivation().getActivationStatus().equals("activated")) {
                resultList.add(f);
            }
        }
        followCountDto.setFollowing(resultList.size());
        List<User> followersList = followRepository.getByFollowedUserId(user.getId());
        List<User> followersResultList = new ArrayList<User>();
        for (User u : followersList) {
            if (u.getUserActivation().getActivationStatus().equals("activated")) {
                followersResultList.add(u);
            }
        }
        followCountDto.setFollowers(followersResultList.size());
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
