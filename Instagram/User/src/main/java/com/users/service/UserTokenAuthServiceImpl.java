package com.users.service;

import com.users.model.User;
import com.users.model.UserTokenAuth;
import com.users.repository.UserTokenAuthRepository;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.UUID;

@Service
@Transactional
public class UserTokenAuthServiceImpl implements UserTokenAuthService {

    @Autowired
    private UserTokenAuthRepository userTokenAuthRepository;

    public void saveToken(User user) {
        UserTokenAuth userTokenAuth = new UserTokenAuth();
        userTokenAuth.setTokenNo(UUID.randomUUID().toString());
        userTokenAuth.setStatus('N');
        userTokenAuth.setUser(user);
        userTokenAuthRepository.save(userTokenAuth);
    }

    public UserTokenAuth getUserByTokenNo(String tokenNo, Long id){
        return userTokenAuthRepository.getUserTokenAuthByTokenNoAAndId(tokenNo, id);

    }

    public UserTokenAuth authUser(String tokenNo) {
        UserTokenAuth isUser = userTokenAuthRepository.getUserTokenAuthByTokenNoAAndId(tokenNo,id);
        if((isUser != null)&& (isUser.getTokenNo()!= null)) {
            UserTokenAuth userTokenAuth = new UserTokenAuth();
            userTokenAuth.setTokenNo(UUID.randomUUID().toString());
            userTokenAuth.setStatus('Y');
        }
        return isUser;
    }
}
