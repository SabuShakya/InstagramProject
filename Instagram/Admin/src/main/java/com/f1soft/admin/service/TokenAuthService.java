package com.f1soft.admin.service;

import com.f1soft.admin.dto.AdminLoginDto;
import com.f1soft.admin.dto.TokenAuthDto;
import com.f1soft.admin.model.Admin;
import com.f1soft.admin.model.TokenAuth;

public interface TokenAuthService {
    public void saveToken(Admin admin);
    public TokenAuthDto authenticateToken(AdminLoginDto adminLoginDto);
    public boolean verifyIfLoggedIn(Admin admin,String tokenNo);
//    public TokenAuth getAdminByTokenNo(String token,String userId);
//    public TokenAuth getByUserId(String userId);
}
