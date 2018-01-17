package com.users.utils;

import com.users.dto.UserTokenDto;
import com.users.model.UserToken;

import java.util.UUID;

public class TokenUtils {
    public static String generateToken(){
        return UUID.randomUUID().toString().replace("-","");
    }

    public static UserTokenDto setUserToken(UserToken userToken){
        UserTokenDto userTokenDto = new UserTokenDto();
        userTokenDto.setTokenNo(userToken.getTokenNo());
        userTokenDto.setStatus(userToken.getStatus());
        userTokenDto.setUsername(userToken.getUser().getUsername());
        return userTokenDto;
    }
}