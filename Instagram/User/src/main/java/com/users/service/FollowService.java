package com.users.service;

import com.users.dto.UserPostDto;

import java.util.List;

public interface FollowService {
    public List<UserPostDto> getPosts(String username);
}
