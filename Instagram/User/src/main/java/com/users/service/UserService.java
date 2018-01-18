package com.users.service;

import com.users.dto.UserSearchDto;
import com.users.dto.Userdto;
import com.users.model.User;

import java.util.List;

public interface UserService {
    public User getUser(String username);
    public void saveUser(User user);
    public List<User> findAllUsers();
    public boolean loginUser(Userdto userdto);
    public List<UserSearchDto> findBySearchTerm(String searchTerm);
}
