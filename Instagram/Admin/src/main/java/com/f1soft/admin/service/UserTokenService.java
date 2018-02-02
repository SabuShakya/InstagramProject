package com.f1soft.admin.service;

import com.f1soft.admin.dto.UserTokenDto;
import com.f1soft.admin.dto.Userdto;
import com.f1soft.admin.model.User;
import com.f1soft.admin.model.UserToken;

public interface UserTokenService {
    public void saveToken(User user);
    UserToken getUserByTokenNo(String tokenNo, Long id);
    public UserTokenDto authToken(Userdto userdto);
    public void logoutUser(long id, String tokenNo);
}
