//package com.f1soft.admin.serviceimpl;
//
//import com.f1soft.admin.dto.UserLogsDto;
//import com.f1soft.admin.service.UserLogService;
//import com.users.model.User;
//import com.users.model.UserPhotos;
//import com.users.model.UserToken;
//import com.users.repository.PhotoRepository;
//import com.users.repository.UserRepository;
//import com.users.repository.UserTokenRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.Date;
//import java.util.List;
//
//@Service
//@Transactional
//public class UserLogServiceImpl implements UserLogService {
//
//    @Autowired
//    private UserRepository userRepository;
//    @Autowired
//    private UserTokenRepository tokenRepository;
//    @Autowired
//    private PhotoRepository photoRepository;
//    public UserLogsDto getUserLogs() {
//        List<User> userList = userRepository.findAll();
//        List<UserToken> activeUsersList = tokenRepository.getActiveUsers();
//        List<UserPhotos> userPhotosList = photoRepository.findAll();
//        List<UserPhotos> uploadsPerDay = photoRepository.getUploadsPerDay(new Date());
//
//        UserLogsDto userLogsDto =  new UserLogsDto();
//        userLogsDto.setTotalUsers(userList.size());
//        userLogsDto.setActiveUsers(activeUsersList.size());
//        userLogsDto.setTotalUploads(userPhotosList.size());
//        userLogsDto.setUploadsPerDay(uploadsPerDay.size());
//        return userLogsDto;
//    }
//}
