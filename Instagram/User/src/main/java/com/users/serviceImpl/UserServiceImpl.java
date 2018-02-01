package com.users.serviceImpl;

import com.users.dto.UserSearchDto;
import com.users.dto.Userdto;
import com.users.model.ProfilePhoto;
import com.users.model.User;
import com.users.repository.ProfilePhotoRepository;
import com.users.repository.UserRepository;
import com.users.service.EmailService;
import com.users.service.ProfilePhotoService;
import com.users.service.UserService;
import com.users.service.UserTokenService;
import com.users.utils.TokenUtils;
import com.users.utils.UserSearchUtils;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional("transactionManager")
public class UserServiceImpl implements UserService {

    @Resource
    private UserRepository userRepository;

    @Resource
    private ProfilePhotoRepository profilePhotoRepository;

    @Autowired
    private UserTokenService userTokenService;

    @Autowired
    public EntityManager em;

    @Autowired
    private EmailService emailService;

    private JavaMailSender mailSender;

    @Autowired
    private ProfilePhotoService profilePhotoService;

    public void saveUser(User user) {
        String password=TokenUtils.generateToken();
        user.setPassword(password);
        user.setAccountStatus("public");
        emailService.sendEmail(user);
        user.setPassword(BCrypt.hashpw(password,BCrypt.gensalt()));
        userRepository.save(user);
        setDefaultProfilePhoto(user);
    }

    public void setDefaultProfilePhoto(User user){
        ProfilePhoto profilePhoto = new ProfilePhoto();
        profilePhoto.setUser(user);
        profilePhoto.setProfile_pic("login");
        profilePhotoRepository.save(profilePhoto);
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

   //smriti
    public boolean loginUser(Userdto userdto) {
        User isUser = userRepository.getUserByUsername(userdto.getUsername());
        if((isUser !=null) && (BCrypt.checkpw(userdto.getPassword(),isUser.getPassword()))){
            userdto.setId(isUser.getId());
            return true;
        }
        return false;
    }

    public List<UserSearchDto> findBySearchTerm(String searchTerm) {
        String sql = "Select u from User u where u.username like :username";
        List<User> userList= em.createQuery(sql,User.class).setParameter("username",
                "%"+searchTerm+"%").getResultList();
       return UserSearchUtils.getSearchedUserInfo(userList);
    }

    public User getUserEmail(String email) {
        return userRepository.getUserByEmail(email);
    }

    public boolean checkPassword(Userdto userdto){
        User isUser = userRepository.getUserByUsername(userdto.getUsername());
        if((isUser !=null) && (BCrypt.checkpw(userdto.getOldPassword(),isUser.getPassword()))){
            return true;
        }
        return false;
    }

    public void updateUser(Userdto userdto){
        User userRepo= userRepository.getUserByUsername(userdto.getUsername());
        userRepo.setPassword(BCrypt.hashpw(userdto.getPassword(),BCrypt.gensalt()));
        userRepository.save(userRepo);
    }

    public void privateAccount(String username){
        User user= userRepository.getUserByUsername(username);
        user.setAccountStatus("private");
        userRepository.save(user);
    }

    public void publicAccount(String username){
        User user = userRepository.getUserByUsername(username);
        user.setAccountStatus("public");
        userRepository.save(user);
    }

    public boolean checkAccountStatus(Userdto userdto){
        User isUser = userRepository.getUserByUsername(userdto.getUsername());
        if((isUser!=null)&& (isUser.getAccountStatus().equals("public"))){
            return true;
        }
        else{
            return false;
        }
    }
}
