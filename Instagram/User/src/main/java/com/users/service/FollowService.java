package com.users.service;

import com.users.dto.FollowDto;
import com.users.dto.UserPostDto;

import java.util.List;

public interface FollowService {
    public List<UserPostDto> getPosts(String username);
    public void saveFollows(FollowDto followDto);
    public boolean checkFollow(FollowDto followDto);
    public void unfollowUser(FollowDto followDto);
}
