package com.users.repository;

import com.users.model.Follow;
import com.users.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FollowRepository extends JpaRepository<Follow,Long>{
    @Query("SELECT f.followedUser From Follow f where f.user.username=:username")
    public List<User> getFollowedUser(@Param("username")String username);

    @Query("SELECT f From Follow f where f.user.username=:username and " +
            "f.followedUser.username=:followingUserName")
    public Follow checkFollow(@Param("username")String username,
                              @Param("followingUserName")String followingUserName);
}
