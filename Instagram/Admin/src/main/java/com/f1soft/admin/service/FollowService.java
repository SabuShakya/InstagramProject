package com.f1soft.admin.service;

import com.f1soft.admin.dto.*;
import com.f1soft.admin.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface FollowService {
    List<User> getFollowedUsers(String username);

    FollowersCountdto getFollowersCount(String username);

    FollowingCountdto getFollowingCount(String username);

    List<UserSearchDto> getFollowersList(String username);

    List<UserSearchDto> getFollowingList(String username);


}
