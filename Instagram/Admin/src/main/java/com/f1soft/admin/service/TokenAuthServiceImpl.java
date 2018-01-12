package com.f1soft.admin.service;

import com.f1soft.admin.dto.AdminLoginDto;
import com.f1soft.admin.dto.TokenAuthDto;
import com.f1soft.admin.model.Admin;
import com.f1soft.admin.model.TokenAuth;
import com.f1soft.admin.repository.TokenRepository;
import com.f1soft.admin.utils.TokenUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Transactional
public class TokenAuthServiceImpl implements TokenAuthService{

    private final TokenRepository tokenRepository;

    public TokenAuthServiceImpl(TokenRepository tokenRepository) {
        this.tokenRepository = tokenRepository;
    }

    public TokenAuthDto authenticateToken(AdminLoginDto adminLoginDto) {
        TokenAuth tokenAuth =  tokenRepository.getByAdmin_Id(adminLoginDto.getId());
        TokenAuthDto tokenAuthDto= new TokenAuthDto();
           if ((tokenAuth.getTokenNo()!= null) && tokenAuth.getStatus() == 'N'){
               tokenAuth.setTokenNo(TokenUtils.generateToken());
               tokenAuth.setStatus('Y');
               tokenAuthDto=TokenUtils.convertTokenAuthToTokenAuthDto(tokenAuth);
               return tokenAuthDto;
           }
           tokenAuthDto =TokenUtils.convertTokenAuthToTokenAuthDto(tokenAuth);
           return tokenAuthDto;
    }

    public boolean verifyIfLoggedIn(Admin admin,String tokenNo) {
        TokenAuth tokenAuth=tokenRepository.getByAdmin_IdAndTokenNo(admin.getId(),tokenNo);
        if ((tokenAuth != null) & (tokenAuth.getStatus().equals('Y'))){
            return true;
        }
        return false;
    }

    public void saveToken(Admin admin) {
        TokenAuth tokenAuth = new TokenAuth();
        tokenAuth.setTokenNo(TokenUtils.generateToken());
        tokenAuth.setStatus('N');
        tokenAuth.setAdmin(admin);
        tokenRepository.save(tokenAuth);
    }
//    public TokenAuth getByUserId(String userId) {
////        return tokenRepository.getByAdmin_UserId(userId);
//        return null
//    }
//
//
//    public TokenAuth getAdminByTokenNo(String token, String userId) {
////        return tokenRepository.getByTokenNoAndAdmin_UserId(token,userId);
//        return null;
//    }
}
