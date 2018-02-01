package com.users.controller;

import com.users.dto.*;
import com.users.model.*;
import com.users.service.*;
import com.users.utils.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.awt.print.Pageable;
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

    @Autowired
    private EmailService emailService;

    @PostMapping("/signup")
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

    //smriti
    @PostMapping("/update")
    public ResponseEntity<MessageDto> updateUser(@RequestBody Userdto userdto){
        boolean isUser = userService.checkPassword(userdto);
        MessageDto messageDto = new MessageDto();
        if(isUser){
           userService.updateUser(userdto);
           messageDto.setMessage("PasswordChanged");
           return new ResponseEntity<MessageDto>(messageDto, HttpStatus.OK);
        }
        messageDto.setMessage("Password didnt match");
        return new ResponseEntity<MessageDto>(messageDto, HttpStatus.NOT_FOUND);
    }

    @GetMapping("/getPosts/{userName}")
    public ResponseEntity<List<UserPostDto>> getPosts(@PathVariable("userName")String username,
    @RequestParam("page") int page, @RequestParam("size") int size){
        org.springframework.data.domain.Pageable pageable = new PageRequest(page,size);
        List<UserPostDto> userPostList=followService.getPosts(username, pageable);
        if (userPostList!= null) {
            return new ResponseEntity<List<UserPostDto>>(userPostList, HttpStatus.OK);
        }
        return new ResponseEntity<List<UserPostDto>>(userPostList, HttpStatus.NOT_FOUND);
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logoutUser(@RequestBody UserTokenDto userTokenDto){
        User user = userService.getUser(userTokenDto.getUsername());
        userTokenService.logoutUser(user.getId(),userTokenDto.getTokenNo());
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @GetMapping("/search/{searchTerm}")
    public ResponseEntity<List<UserSearchDto>> searchUsers(@PathVariable("searchTerm")String searchTerm){
        List<UserSearchDto> list = userService.findBySearchTerm(searchTerm);
        if (list!=null) {
            return new ResponseEntity<List<UserSearchDto>>(list, HttpStatus.OK);
        }
        return new ResponseEntity<List<UserSearchDto>>(list, HttpStatus.NOT_FOUND);
    }

    @PostMapping("/makePrivate/{username}")
    public ResponseEntity<Boolean> privateAccount(@PathVariable("username") String username){
        userService.privateAccount(username);
        return new ResponseEntity<Boolean>(true, HttpStatus.OK);
    }

    @PostMapping("/makePublic/{username}")
    public ResponseEntity<Boolean> publicAccount(@PathVariable("username") String username){
        userService.publicAccount(username);
        return new ResponseEntity<Boolean>(true, HttpStatus.OK);
    }
}
