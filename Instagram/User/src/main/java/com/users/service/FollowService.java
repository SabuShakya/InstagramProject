package com.users.service;

import com.users.dto.*;
import com.users.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface FollowService {
    public List<User> getFollowedUsers(String username);

    public void saveFollows(FollowDto followDto);
    public boolean checkFollow(FollowDto followDto);
    public void unfollowUser(FollowDto followDto);
//    public FollowCountDto getFollowCount(String username);
    public FollowersCountdto getFollowersCount(String username);
    public FollowingCountdto getFollowingCount(String username);

    public List<UserSearchDto> getFollowersList(String username);
    public List<UserSearchDto> getFollowingList(String username);

}
