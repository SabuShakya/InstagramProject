package com.users.repository;

import com.users.model.BlockUser;
import com.users.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BlockRepository extends JpaRepository<BlockUser,Long>{

    @Query("SELECT b From BlockUser b where b.user.username=:username and " +
            "b.blockedUser.username=:blockedUsername")
    public BlockUser checkBlock(@Param("username")String username,
                              @Param("blockedUsername")String blockedUsername);

    @Query("select b.user from BlockUser b where b.blockedUser.id=:id")
    public List<User> getByBlockedUser(@Param("id")long id);
}
