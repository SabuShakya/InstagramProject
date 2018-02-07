package com.users.serviceImpl;

import com.users.dto.UserActivationDto;
import com.users.dto.Userdto;
import com.users.model.User;
import com.users.model.UserActivation;
import com.users.repository.UserActivationRepository;
import com.users.repository.UserRepository;
import com.users.service.UserActivationService;
import com.users.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserActivationServiceImpl implements UserActivationService {

    @Autowired
    private UserActivationRepository userActivationRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    public void saveUserActivationStatus(User user){
        UserActivation userActivation= new UserActivation();
        userActivation.setActivationStatus("activated");
        userActivation.setUser(user);
        userActivationRepository.save(userActivation);
    }

    public void deactivateAccount(String username){
        User user= userRepository.getUserByUsername(username);
        UserActivation userActivation= userActivationRepository.getByUserUsername(username);
        userActivation.setUser(user);
        userActivation.setActivationStatus("deactivated");
        userActivationRepository.save(userActivation);
    }

    public boolean checkActivationStatus(UserActivationDto userActivationDto){
        UserActivation isActive=userActivationRepository.getByUserUsername(userActivationDto.getUsername());
        if((isActive!=null)&&(isActive.getActivationStatus()=="activated")){
            return true;
        }
        return false;
    }
}
