package com.users.service;

import com.users.model.User;
import com.users.repository.UserRepository;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@Transactional("transactionManager")
public class UserServiceImpl implements UserService {

    @Resource
    private UserRepository userRepository;

    public void saveUser(User user) {
        user.setPassword(BCrypt.hashpw(user.getPassword(),BCrypt.gensalt()));
        userRepository.save(user);
    }

    public User getUser(String username) {
        User user = userRepository.getUserByUsername(username);
        return user;
    }

    public List<User> findAllUsers() {
        List<User> userlist = userRepository.findAll();
        System.out.println(userlist.toString());
        return userlist;

    }
    public User getUserByTokenNo(String token, String username){
        return userRepository.getUserByTokenNoAndUsername(token,username);

    }

//    public User findByEmail(String email){
//        return userRepository.findByEmail(email);
//    }

    }


