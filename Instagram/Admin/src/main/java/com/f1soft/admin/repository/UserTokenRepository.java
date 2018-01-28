package com.f1soft.admin.repository;

import com.f1soft.admin.model.UserToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserTokenRepository extends JpaRepository<UserToken, Long> {
    @Query("select u from UserToken u where u.status ='Y'")
    public List<UserToken> getActiveUsers();
}
