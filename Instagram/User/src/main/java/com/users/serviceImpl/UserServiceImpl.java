package com.users.serviceImpl;

import com.users.dto.FollowDto;
import com.users.dto.SearchListDto;
import com.users.dto.UserSearchDto;
import com.users.dto.Userdto;
import com.users.model.Follow;
import com.users.model.ProfilePhoto;
import com.users.model.User;
import com.users.model.UserActivation;
import com.users.repository.ProfilePhotoRepository;
import com.users.repository.UserActivationRepository;
import com.users.repository.UserRepository;
import com.users.service.*;
import com.users.utils.TokenUtils;
import com.users.utils.UserSearchUtils;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.persistence.*;
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
    private UserActivationRepository userActivationRepository;

    @Autowired
    private UserTokenService userTokenService;
    @Autowired
    private FollowService followService;
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

    public boolean loginUser(Userdto userdto) {
        User isUser = userRepository.getUserByUsername(userdto.getUsername());

        if((isUser !=null) && (BCrypt.checkpw(userdto.getPassword(),isUser.getPassword()))){
            userdto.setId(isUser.getId());
            UserActivation userActivation = userActivationRepository.getByUserUsername(userdto.getUsername());
            userActivation.setActivationStatus("activated");
            userActivationRepository.save(userActivation);
            return true;
        }
        return false;
    }

//    public List<UserSearchDto> findBySearchTerm(String searchTerm) {
//        String sql="SELECT u.username,uAt.activationStatus FROM user_table u JOIN userActivation_table uAt ON u.id = uAt.user_id WHERE u.username LIKE :searchTerm and  uAt.activationStatus LIKE :status";
//        Query query=em.createNativeQuery(sql,"SearchListResults");
////        query.setParameter("searchTerm","%"+searchTerm+"%").setParameter("status","activated");
////        List<User> userList=query.getResultList();
//       return UserSearchUtils.getSearchedUserInfo(userList);
//    }

    public List<UserSearchDto> findBySearchTerm(String searchTerm,String username) {
        String sql = "Select u from User u where u.username like :username";
        List<User> userList= em.createQuery(sql,User.class).setParameter("username",
                "%"+searchTerm+"%").getResultList();
        List<UserSearchDto> list = UserSearchUtils.getSearchedUserInfo(userList);
        List<UserSearchDto> returnlist = new ArrayList<UserSearchDto>();
        for (UserSearchDto user :list){
            FollowDto followDto = new FollowDto();
            followDto.setUserName(username);
            followDto.setFollowing_userName(user.getUsername());
            user.setShowResultButtons(followService.checkFollow(followDto));
            returnlist.add(user);
        }
        return returnlist;
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

    public boolean checkAccountStatus(String username){
        User isUser = userRepository.getUserByUsername(username);
        if((isUser!=null)&& (isUser.getAccountStatus().equals("public"))){
            return true;
        }
        else{
            return false;
        }
    }
}
