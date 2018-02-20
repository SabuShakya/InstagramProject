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

    @Autowired
    private UserActivationService userActivationService;

    @Autowired
    private BlockService blockService;

    @PostMapping("/signup")
    public ResponseEntity<Boolean> createUser(@RequestBody User user) {
        userService.saveUser(user);
        userTokenService.saveToken(user);
        userActivationService.saveUserActivationStatus(user);
        return new ResponseEntity<Boolean>(true, HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<UserTokenDto> getUser(@RequestBody Userdto userdto) {
        boolean isUser = userService.loginUser(userdto);
        UserTokenDto userTokenDto = new UserTokenDto();
        if (isUser) {
            userTokenDto = userTokenService.authToken(userdto);
            return new ResponseEntity<UserTokenDto>(userTokenDto, HttpStatus.OK);
        }
        return new ResponseEntity<UserTokenDto>(userTokenDto, HttpStatus.NOT_FOUND);
    }

    @PostMapping("/update")
    public ResponseEntity<Boolean> updateUser(@RequestBody Userdto userdto) {
        boolean isUser = userService.checkPassword(userdto);
        if (isUser) {
            userService.updateUser(userdto);
            return new ResponseEntity<Boolean>(true, HttpStatus.OK);
        }
        return new ResponseEntity<Boolean>(false, HttpStatus.NOT_FOUND);
    }

    @PostMapping("/checkPassword")
    public ResponseEntity<Boolean> checkPassword(@RequestBody Userdto userdto) {
        boolean isUser = userService.loginUser(userdto);
        if (isUser) {
            return new ResponseEntity<Boolean>(true, HttpStatus.OK);
        }
        return new ResponseEntity<Boolean>(false, HttpStatus.NOT_FOUND);
    }

    @GetMapping("/getPosts/{userName}")
    public ResponseEntity<List<UserPostDto>> getPosts(@PathVariable("userName") String username, @RequestParam("page") int page, @RequestParam("size") int size) {
        org.springframework.data.domain.Pageable pageable = new PageRequest(page, size);
        List<UserPostDto> userPostList = photoService.getPosts(username, pageable);
        if (userPostList != null) {
//                && !userPostList.isEmpty()){
            return new ResponseEntity<List<UserPostDto>>(userPostList, HttpStatus.OK);
        }
        return new ResponseEntity<List<UserPostDto>>(userPostList, HttpStatus.NOT_FOUND);
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logoutUser(@RequestBody UserTokenDto userTokenDto) {
        User user = userService.getUser(userTokenDto.getUsername());
        userTokenService.logoutUser(user.getId(), userTokenDto.getTokenNo());
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @GetMapping("/search/{searchTerm}/{username}")
    public ResponseEntity<List<UserSearchDto>> searchUsers(@PathVariable("searchTerm") String searchTerm, @PathVariable("username") String username) {
        List<UserSearchDto> list = userService.findBySearchTerm(searchTerm, username);
        if (list != null) {
            return new ResponseEntity<List<UserSearchDto>>(list, HttpStatus.OK);
        }
        return new ResponseEntity<List<UserSearchDto>>(list, HttpStatus.NOT_FOUND);
    }

    @GetMapping("/anguSearch/{userName}/{searchTerm}")
    public ResponseEntity<List<UserSearchDto>> anguSearchUsers(@PathVariable("userName") String userName
            , @PathVariable("searchTerm") String searchTerm) {
        List<UserSearchDto> list = userService.findByAnguSearchTerm(searchTerm,userName);
        if (list != null) {
            return new ResponseEntity<List<UserSearchDto>>(list, HttpStatus.OK);
        }
        return new ResponseEntity<List<UserSearchDto>>(list, HttpStatus.NOT_FOUND);
    }


    @PostMapping("/makePrivate/{username}")
    public ResponseEntity<Boolean> privateAccount(@PathVariable("username") String username) {
        userService.privateAccount(username);
        return new ResponseEntity<Boolean>(true, HttpStatus.OK);
    }

    @PostMapping("/makePublic/{username}")
    public ResponseEntity<Boolean> publicAccount(@PathVariable("username") String username) {
        userService.publicAccount(username);
        return new ResponseEntity<Boolean>(true, HttpStatus.OK);
    }

    @PostMapping("/checkPrivacy/{username}")
    public ResponseEntity<Boolean> checkPrivacy(@PathVariable("username") String username) {
        boolean isPublic = userService.checkAccountStatus(username);
        if (isPublic) {
            return new ResponseEntity<Boolean>(true, HttpStatus.OK);
        }
        return new ResponseEntity<Boolean>(false, HttpStatus.NOT_FOUND);
    }

    @PostMapping("/deActivateAccount/{username}")
    public ResponseEntity<Boolean> deActivateAccount(@PathVariable("username") String username) {
        userActivationService.deactivateAccount(username);
        return new ResponseEntity<Boolean>(true, HttpStatus.OK);
    }

}
