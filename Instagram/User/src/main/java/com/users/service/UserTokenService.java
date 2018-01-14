package com.users.service;

import com.users.dto.UserTokenDto;
import com.users.dto.Userdto;
import com.users.model.User;
import com.users.model.UserToken;

public interface UserTokenService {
    public void saveToken(User user);
    UserToken getUserByTokenNo(String tokenNo, Long id);

    public UserTokenDto authToken(Userdto userdto);
    public boolean verifyloginUser(User user, String tokenNo);

}
