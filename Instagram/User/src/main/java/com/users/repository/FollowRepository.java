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

    public List<Follow> getByUserId(long userId);


    @Query("select f.followedUser from Follow f where f.user.id=:id")
    public List<User> getByFollowingUserId(@Param("id")long id);

    @Query("select f.user from Follow f where f.followedUser.id=:id")
    public List<User> getByFollowedUserId(@Param("id")long id);
}
