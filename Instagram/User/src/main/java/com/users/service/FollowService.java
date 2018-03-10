package com.users.service;

import com.users.dto.FollowDto;
import com.users.dto.FollowersCountdto;
import com.users.dto.FollowingCountdto;
import com.users.dto.UserSearchDto;
import com.users.model.User;

import java.util.List;

public interface FollowService {
    List<User> getFollowedUsers(String username);

    void saveFollows(FollowDto followDto);

    boolean checkFollow(FollowDto followDto);

    void unfollowUser(FollowDto followDto);

    FollowersCountdto getFollowersCount(String username);

    FollowingCountdto getFollowingCount(String username);

    List<UserSearchDto> getFollowersList(String username, String loggedInUserName);

    List<UserSearchDto> getFollowingList(String username, String loggedInUserName);

    List<UserSearchDto> convertToUserSearchDtoList(List<User> followersList, String username, User user, String loggedInUserName);
}
