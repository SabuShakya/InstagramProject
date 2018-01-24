package com.users.repository;

import com.users.model.UserToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserTokenRepository extends JpaRepository<UserToken, Long> {
    public UserToken getUserByTokenNoAndId(String tokenNo, long id);
    public UserToken getByUserId(long id);
}
