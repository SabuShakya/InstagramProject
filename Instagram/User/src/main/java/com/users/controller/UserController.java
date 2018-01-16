package com.users.controller;

import com.users.dto.UserPhotodto;
import com.users.dto.UserPostDto;
import com.users.dto.UserTokenDto;
import com.users.dto.Userdto;
import com.users.model.User;
import com.users.model.UserPhotos;
import com.users.model.UserToken;
import com.users.service.FollowService;
import com.users.service.PhotoService;
import com.users.service.UserService;
import com.users.service.UserTokenService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private PhotoService photoService;

    @Autowired
    private UserTokenService userTokenService;

    @Autowired
    private FollowService followService;

    @PostMapping(value = "/signup", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Boolean> createUser(@RequestBody User user) {
        userService.saveUser(user);
        userTokenService.saveToken(user);
        return new ResponseEntity<Boolean>(true, HttpStatus.OK);
    }


    @PostMapping("/login")
    public ResponseEntity<UserTokenDto> getUser(@RequestBody Userdto userdto){
        boolean isUser = userService.loginUser(userdto);

        UserTokenDto userTokenDto = new UserTokenDto();
        if(isUser){
            userTokenDto = userTokenService.authToken(userdto);
            return new ResponseEntity<UserTokenDto>(userTokenDto,HttpStatus.OK);
        }
        return new ResponseEntity<UserTokenDto>(userTokenDto, HttpStatus.NOT_FOUND);
    }

   @PostMapping("/upload")
   public ResponseEntity<Boolean> uploads(@RequestBody UserPhotodto userPhotodto){
        photoService.savePhoto(userPhotodto);
        return new ResponseEntity<Boolean>(true,HttpStatus.OK);
   }

    @GetMapping("/allPhotos")
    public ResponseEntity<List<UserPhotodto>> photoList(){
        List<UserPhotodto> photoList= photoService.getAllPhotos();
        if(photoList != null && !photoList.isEmpty()){
            return new ResponseEntity<List<UserPhotodto>>(photoList,HttpStatus.OK);
        }
        return new ResponseEntity<List<UserPhotodto>>(photoList,HttpStatus.NOT_FOUND);
    }

    @GetMapping("/getPosts/{userName}")
    public ResponseEntity<List<UserPostDto>> getPosts(@PathVariable("userName")String username){
        List<UserPostDto> userPostList=followService.getPosts(username);
        if (userPostList!= null) {
            return new ResponseEntity<List<UserPostDto>>(userPostList, HttpStatus.OK);
        }
        return new ResponseEntity<List<UserPostDto>>(userPostList, HttpStatus.NOT_FOUND);
    }
}
