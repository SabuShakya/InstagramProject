package com.users.repository;

import com.users.model.UserTokenAuth;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserTokenAuthRepository extends JpaRepository<UserTokenAuth, Long> {
    UserTokenAuth getUserTokenAuthByTokenNoAAndId(String tokenNo, long id);
    UserTokenAuth getById(long id);
}
