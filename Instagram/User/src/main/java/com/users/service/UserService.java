package com.users.service;

import com.users.dto.UserSearchDto;
import com.users.dto.Userdto;
import com.users.model.User;

import java.util.List;

public interface UserService {
    public User getUser(String username);
    public User getUserEmail(String email);
    public void saveUser(User user);
    public List<User> findAllUsers();
    public boolean loginUser(Userdto userdto);

    public List<UserSearchDto> findByAnguSearchTerm(String searchTerm);
    public List<UserSearchDto> findBySearchTerm(String searchTerm,String username);
    public void updateUser(Userdto userdto);
    public boolean checkPassword(Userdto userdto);
    public void privateAccount(String username);
    public void publicAccount(String username);
    public boolean checkAccountStatus(String username);
}
