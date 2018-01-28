package com.f1soft.admin.utils;

import com.f1soft.admin.dto.TokenAuthDto;
import com.f1soft.admin.model.TokenAuth;

import java.util.UUID;

public class TokenUtils {
    public static String generateToken(){
        return UUID.randomUUID().toString().replace("-","");
    }
    public static TokenAuthDto convertTokenAuthToTokenAuthDto(TokenAuth tokenAuth){
        TokenAuthDto tokenAuthDto = new TokenAuthDto();
        tokenAuthDto.setTokenNo(tokenAuth.getTokenNo());
        tokenAuthDto.setStatus(tokenAuth.getStatus());
        tokenAuthDto.setName(tokenAuth.getAdmin().getName());
        tokenAuthDto.setUserName(tokenAuth.getAdmin().getUserName());
        return tokenAuthDto;
    }
}
