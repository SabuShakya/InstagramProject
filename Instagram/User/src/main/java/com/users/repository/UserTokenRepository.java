package com.users.repository;

import com.users.model.UserToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserTokenRepository extends JpaRepository<UserToken, Long> {
    public UserToken findByUser_IdAndAndTokenNo(long id,String tokenNo);
    public UserToken getByUserId(long id);
    @Query("select u from UserToken u where u.status ='Y'")
    public List<UserToken> getActiveUsers();
}
