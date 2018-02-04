package com.users.service;

import com.users.dto.UserActivationDto;
import com.users.dto.Userdto;
import com.users.model.User;
import com.users.model.UserActivation;

public interface UserActivationService {
    public void saveUserActivationStatus(User user);
    public void deactivateAccount(String username);
    public boolean checkActivationStatus(UserActivationDto userActivationDto);
}
