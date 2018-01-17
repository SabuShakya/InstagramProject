package com.users.controller;

import com.sun.org.apache.xpath.internal.operations.Bool;
import com.users.dto.Commentsdto;
import com.users.dto.UserPhotodto;
import com.users.dto.UserTokenDto;
import com.users.dto.Userdto;
import com.users.model.User;
import com.users.model.UserPhotos;
import com.users.model.UserToken;
import com.users.service.CommentsService;
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
    private CommentsService commentsService;

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

    @PostMapping("/upload")
    public ResponseEntity<Boolean> uploads(@RequestBody UserPhotodto userPhotodto){
        photoService.savePhoto(userPhotodto);
        return new ResponseEntity<Boolean>(true,HttpStatus.OK);
    }

    @GetMapping("/allPhotos/{username}")
    public ResponseEntity<List<UserPhotodto>> photoList(@PathVariable("username") String username){
        List<UserPhotodto> photoList= photoService.getAllPhotos(username);
        if(photoList != null && !photoList.isEmpty()){
            return new ResponseEntity<List<UserPhotodto>>(photoList,HttpStatus.OK);
        }
        return new ResponseEntity<List<UserPhotodto>>(photoList,HttpStatus.NOT_FOUND);
    }

    @PostMapping("/addComment")
    public ResponseEntity<Boolean> addComment( @RequestBody Commentsdto commentsdto ){
        commentsService.saveComments(commentsdto);
        return new ResponseEntity<Boolean>(true,HttpStatus.OK);
    }

    @GetMapping("/showComments/{image_path}")
    public ResponseEntity<List<Commentsdto>> commentList(@PathVariable("image_path")String image_path){
        String image= image_path+".jpg";
        List<Commentsdto> commentList=commentsService.getAllComments(image);
        if(commentList!= null && !commentList.isEmpty()){
            return new ResponseEntity<List<Commentsdto>>(commentList,HttpStatus.OK);
        }
        return new ResponseEntity<List<Commentsdto>>(commentList, HttpStatus.NOT_FOUND);
    }
    
    @PostMapping("/logout")
    public ResponseEntity<Void> logoutUser(@RequestBody UserTokenDto userTokenDto){
        User user = userService.getUser(userTokenDto.getUsername());
        userTokenService.logoutUser(user.getId(),userTokenDto.getTokenNo());
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @PostMapping("/updateProfile")
    public ResponseEntity<Boolean> updateProfile(@RequestBody UserPhotodto userPhotodto){
        photoService.updateProfile(userPhotodto);
        return new ResponseEntity<Boolean>(true,HttpStatus.OK);
    }
}
