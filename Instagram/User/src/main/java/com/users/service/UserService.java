package com.users.service;

import com.users.model.User;

import java.util.ArrayList;
import java.util.List;

public interface UserService {
    public User getUser(String uname);
    public void saveUser(User user);
    public List<User> findAllUsers();
//    public User getUserByUname(String uname);

//    public User findByEmail(String email);
//    public User findByConfirmationToken(String confirmationToken);

    public User getUserByTokenNo(String token,String uname);
}
