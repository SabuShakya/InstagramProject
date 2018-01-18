package com.users.controller;

import com.sun.org.apache.xpath.internal.operations.Bool;
import com.users.dto.*;
import com.users.model.User;
import com.users.model.UserPhotos;
import com.users.model.UserToken;
import com.users.service.*;
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
    @Autowired
    private FollowService followService;

    @Autowired
    private LikesService likesService;

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

    @GetMapping("/getPosts/{userName}")
    public ResponseEntity<List<UserPostDto>> getPosts(@PathVariable("userName")String username){
        List<UserPostDto> userPostList=followService.getPosts(username);
        if (userPostList!= null) {
            return new ResponseEntity<List<UserPostDto>>(userPostList, HttpStatus.OK);
        }
        return new ResponseEntity<List<UserPostDto>>(userPostList, HttpStatus.NOT_FOUND);
    }
    @PostMapping("/addComment")
    public ResponseEntity<Boolean> addComment( @RequestBody Commentsdto commentsdto ){
        commentsService.saveComments(commentsdto);
        return new ResponseEntity<Boolean>(true,HttpStatus.OK);
    }

    @GetMapping(value = "/showComments/{image_path}")
    public ResponseEntity<List<Commentsdto>> commentList(@PathVariable("image_path")String image_path){
        List<Commentsdto> commentsdtoList=commentsService.getAllComments(image_path);
        if(commentsdtoList!=null){
        return new ResponseEntity<List<Commentsdto>>(commentsdtoList,HttpStatus.OK);
    }
        return new ResponseEntity<List<Commentsdto>>(commentsdtoList,HttpStatus.NO_CONTENT);
    }

    @PostMapping(value="/addLikes")
    public ResponseEntity<Boolean> addLikes(@RequestBody Likesdto likesdto){
        likesService.saveLikes(likesdto);
        return new ResponseEntity<Boolean>(true,HttpStatus.OK);
    }

    @GetMapping(value="/showLikes/{image_path}")
    public ResponseEntity<List<Likesdto>> likesList(@PathVariable("image_path")String image_path){
        List<Likesdto> likesdtoList = likesService.getAllLikes(image_path);
        if(likesdtoList!=null){
            return new ResponseEntity<List<Likesdto>>(likesdtoList,HttpStatus.OK);
        }
        return new ResponseEntity<List<Likesdto>>(likesdtoList, HttpStatus.NO_CONTENT);
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logoutUser(@RequestBody UserTokenDto userTokenDto){
        User user = userService.getUser(userTokenDto.getUsername());
        userTokenService.logoutUser(user.getId(),userTokenDto.getTokenNo());
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @GetMapping("/search/{searchTerm}")
    public ResponseEntity<User> searchUsers(@PathVariable("searchTerm")String searchTerm){
        List<User> list = userService.findBySearchTerm(searchTerm);
        return new ResponseEntity<User>(HttpStatus.OK);
    }

//    @PostMapping("/updateProfile")
//    public ResponseEntity<Boolean> updateProfile(@RequestBody UserPhotodto userPhotodto){
//        photoService.updateProfile(userPhotodto);
//        return new ResponseEntity<Boolean>(true,HttpStatus.OK);
//    }
}
