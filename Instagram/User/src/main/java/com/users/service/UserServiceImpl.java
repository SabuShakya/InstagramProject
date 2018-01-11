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

    public User getUser(String uname) {
        User user = userRepository.getUserByUname(uname);
        System.out.println(user);
        if (user.getTokenNo()== null){
            String token = UUID.randomUUID().toString().replace("-","");
            System.out.println(token);
            user.setTokenNo(token);
            userRepository.save(user);
            return user;
        }
        return user;
    }

    public List<User> findAllUsers() {
        List<User> userlist = userRepository.findAll();
        System.out.println(userlist.toString());
        return userlist;

    }

//    public User findByEmail(String email){
//        return userRepository.findByEmail(email);
//    }
//
//    public User findByConfirmationToken(String confirmationToken){
//        return userRepository.findByConfirmationToken(confirmationToken);
//    }

    public User getUserByTokenNo(String token, String uname){
        return userRepository.getUserByTokenNoAndUname(token,uname);

    }
}


