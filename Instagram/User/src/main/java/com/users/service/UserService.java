package com.users.service;

import com.users.dto.Userdto;
import com.users.model.User;

import java.util.List;

public interface UserService {
    public User getUser(String username);
    public User getUserEmail(String email);
//    public void sendEmail(User user);
    public void saveUser(User user);
    public List<User> findAllUsers();
    public boolean loginUser(Userdto userdto);
    public List<User> findBySearchTerm(String searchTerm);
}
