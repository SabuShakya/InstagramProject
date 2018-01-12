package com.users.service;

import com.users.model.User;
import com.users.model.UserTokenAuth;

public interface UserTokenAuthService {
    public void saveToken(User user);
    UserTokenAuth getUserByTokenNo(String tokenNo, Long id);
//    UserTokenAuth getUserById(Long id);

    UserTokenAuth authUser(String tokenNo, Long id);
}
