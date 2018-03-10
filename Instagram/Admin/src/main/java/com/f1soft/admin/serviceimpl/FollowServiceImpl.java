package com.f1soft.admin.serviceimpl;

import com.f1soft.admin.dto.*;
import com.f1soft.admin.model.Follow;
import com.f1soft.admin.model.User;
import com.f1soft.admin.repository.CommentsRepository;
import com.f1soft.admin.repository.FollowRepository;
import com.f1soft.admin.repository.UserRepository;
import com.f1soft.admin.service.CommentsService;
import com.f1soft.admin.service.FollowService;

import com.f1soft.admin.service.UserPhotosService;
import com.f1soft.admin.utils.FollowUtils;
import com.f1soft.admin.utils.UserSearchUtils;
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
    private UserPhotosService photoService;

    @Autowired
    private CommentsService commentsService;

    @Autowired
    private CommentsRepository commentsRepository;

    public List<User> getFollowedUsers(String username) {
        List<User> listOfFollowedUsers = followRepository.getFollowedUser(username);
        System.out.println(listOfFollowedUsers);
        return listOfFollowedUsers;
    }

    public FollowersCountdto getFollowersCount(String username) {
        FollowersCountdto followersCountDto = new FollowersCountdto();
        User user = userRepository.getUserByUsername(username);
        List<User> followersList = followRepository.getByFollowedUserId(user.getId());
        List<User> followersResultList = new ArrayList<User>();
        for (User u : followersList) {
            if (u.getUserActivation().getActivationStatus().equals("activated")) {
                followersResultList.add(u);
            }
        }
        followersCountDto.setFollowers(followersResultList.size());
        return followersCountDto;
    }

    public FollowingCountdto getFollowingCount(String username) {
        FollowingCountdto followCountDto = new FollowingCountdto();
        User user = userRepository.getUserByUsername(username);
        List<Follow> followingList = followRepository.getByUserId(user.getId());
        List<Follow> resultList = new ArrayList<Follow>();
        for (Follow f : followingList) {
            if (f.getFollowedUser().getUserActivation().getActivationStatus().equals("activated")) {
                resultList.add(f);
            }
        }
        followCountDto.setFollowing(resultList.size());
        return followCountDto;
    }

    public List<UserSearchDto> getFollowersList(String username) {
        User user = userRepository.getUserByUsername(username);
        List<User> followersList = followRepository.getByFollowedUserId(user.getId());
        List<User> tempList = new ArrayList<User>();
        for (User user1 : followersList) {
            if ((tempList != null) && (user1.getUserActivation().getActivationStatus().equals("activated"))) {
                tempList.add(user1);
            }
        }
        return convertToUserSearchDtoList(tempList, username, user);
    }

    public List<UserSearchDto> getFollowingList(String username) {
        User user = userRepository.getUserByUsername(username);
        List<User> followingList = followRepository.getByFollowingUserId(user.getId());
        List<User> tempList = new ArrayList<User>();
        for (User user1 : followingList) {
            if (tempList != null && (user1.getUserActivation().getActivationStatus().equals("activated"))) {
                tempList.add(user1);
            }
        }
        return convertToUserSearchDtoList(tempList, username, user);
    }

    public List<UserSearchDto> convertToUserSearchDtoList(List<User> followersList, String username, User user) {
        List<UserSearchDto> list = UserSearchUtils.getSearchedUserInfo(followersList);
        List<UserSearchDto> returnlist = new ArrayList<UserSearchDto>();
        for (UserSearchDto userSearchDto : list) {
            FollowDto followDto = new FollowDto();
            followDto.setUserName(username);
            followDto.setFollowing_userName(userSearchDto.getUsername());
            //checking blockstatus
            BlockUserdto blockUserdto = new BlockUserdto();
            blockUserdto.setUserName(username);
            blockUserdto.setBlockedUsername(userSearchDto.getUsername());
            returnlist.add(userSearchDto);
        }
        return returnlist;
    }
}
