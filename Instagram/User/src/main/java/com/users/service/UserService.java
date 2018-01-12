package com.users.service;

import com.users.model.User;
import com.users.model.UserTokenAuth;

import java.util.ArrayList;
import java.util.List;

public interface UserService {
    public User getUser(String username);
    public void saveUser(User user);
    public List<User> findAllUsers();
    public User getUserByTokenNo(String token,String username);
}
