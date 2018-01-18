package com.users.serviceImpl;

import com.users.dto.Userdto;
import com.users.model.User;
import com.users.repository.UserRepository;
import com.users.service.UserService;
import com.users.service.UserTokenService;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
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

    @Autowired
    private UserTokenService userTokenService;

    private JavaMailSender mailSender;

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

    public boolean loginUser(Userdto userdto) {
        User isUser = userRepository.getUserByUsername(userdto.getUsername());
        if((isUser !=null) && BCrypt.checkpw(userdto.getPassword(),isUser.getPassword())){
            userdto.setId(isUser.getId());
            return true;
        }
        return false;
    }

    public List<User> findBySearchTerm(String searchTerm) {
        return userRepository.findByUsername(searchTerm);
    }

    public User getUserEmail(String email) {
        return userRepository.getUserByEmail(email);
    }

//    public void sendEmail(User user){
//        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
//        simpleMailMessage.setTo(userRepository.getUserByEmail(email));
//
//
//    }
}
