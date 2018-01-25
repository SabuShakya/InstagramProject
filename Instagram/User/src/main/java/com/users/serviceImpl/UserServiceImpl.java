package com.users.serviceImpl;

import com.users.dto.UserSearchDto;
import com.users.dto.Userdto;
import com.users.model.ProfilePhoto;
import com.users.model.User;
import com.users.repository.ProfilePhotoRepository;
import com.users.repository.UserRepository;
import com.users.service.EmailService;
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

    public void saveUser(User user) {
        String password=TokenUtils.generateToken();
        user.setPassword(password);
        emailService.sendEmail(user);
        user.setPassword(BCrypt.hashpw(password,BCrypt.gensalt()));
        userRepository.save(user);
        setDefaultProfilePhoto(user);
    }

    public void setDefaultProfilePhoto(User user){
        ProfilePhoto profilePhoto = new ProfilePhoto();
        profilePhoto.setUser(user);
        profilePhoto.setProfile_pic("login");
        profilePhoto.setPhotoStatus('Y');
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

  //smriti
    public User getUserEmail(String email) {
        return userRepository.getUserByEmail(email);
    }

    public void updateUser(User user){
        User userRepo= userRepository.getUserByUsername(user.getUsername());
        userRepo.setUsername(user.getUsername());
        userRepo.setPassword(BCrypt.hashpw(user.getPassword(),BCrypt.gensalt()));
        userRepository.save(userRepo);
    }
}
