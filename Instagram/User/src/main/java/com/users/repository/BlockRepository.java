package com.users.repository;

import com.users.model.BlockUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BlockRepository extends JpaRepository<BlockUser,Long>{

    @Query("SELECT b From BlockUser b where b.user.username=:username and " +
            "b.blockedUser.username=:blockedUsername")
    public BlockUser checkBlock(@Param("username")String username,
                              @Param("blockedUsername")String blockedUsername);

}
