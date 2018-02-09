package com.f1soft.admin.service;

import com.f1soft.admin.dto.FollowCountDto;
import com.f1soft.admin.dto.FollowDto;
import com.f1soft.admin.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface FollowService {
    public List<User> getFollowedUsers(String username);

    public void saveFollows(FollowDto followDto);
    public boolean checkFollow(FollowDto followDto);
    public void unfollowUser(FollowDto followDto);
    public FollowCountDto getFollowCount(String username);
    public List<FollowDto> getFollowersList(String username);
    public List<FollowDto> getFollowingList(String username);

}
