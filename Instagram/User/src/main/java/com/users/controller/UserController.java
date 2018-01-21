package com.users.controller;

import com.users.dto.*;
import com.users.model.*;
import com.users.service.*;
import com.users.utils.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sun.plugin.dom.core.Comment;

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

    @Autowired
    private EmailService emailService;

    @Autowired
    private ProfilePhotoService profilePhotoService;

    @PostMapping("/signup")
    public ResponseEntity<Boolean> createUser(@RequestBody User user) {
        userService.saveUser(user);
        userTokenService.saveToken(user);
        return new ResponseEntity<Boolean>(true, HttpStatus.OK);
    }

    //smriti
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
    public ResponseEntity<Boolean> updateUser(@RequestBody User user){
        userService.updateUser(user);
        return new ResponseEntity<Boolean>(true, HttpStatus.OK);
    }

    @PostMapping("/upload")
    public ResponseEntity<Boolean> uploads(@RequestBody UserPhotodto userPhotodto){
        photoService.savePhoto(userPhotodto);
        return new ResponseEntity<Boolean>(true,HttpStatus.OK);
    }

    //smriti
    @PostMapping("/uploadProfilePhoto")
    public ResponseEntity<Boolean> uploadPhoto(@RequestBody ProfilePhotoDto profilePhotoDto){
        profilePhotoService.savePhoto(profilePhotoDto);
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

    //smriti
    @GetMapping("/ProfilePhotos/{username}")
    public ResponseEntity<ProfilePhotoDto> profilephoto(@PathVariable("username")String username){
       ProfilePhotoDto profilePhotoDto= profilePhotoService.getProfilePhoto(username);
        return new ResponseEntity<ProfilePhotoDto>(profilePhotoDto,HttpStatus.OK);
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

    @PostMapping("/deleteComment")
    public ResponseEntity<Boolean> deleteComment(@RequestBody Commentsdto commentsdto){
        commentsService.deleteComment(commentsdto);
        return new ResponseEntity<Boolean>(true,HttpStatus.OK);
    }

    @PostMapping("/editComment")
    public ResponseEntity<Boolean> editComment(@RequestBody Commentsdto commentsdto){
        commentsService.updateComment(commentsdto);
        return new ResponseEntity<Boolean>(true,HttpStatus.OK);
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
//sabu
//    @GetMapping("/search/{searchTerm}")
//    public ResponseEntity<List<UserSearchDto>> searchUsers(@PathVariable("searchTerm")String searchTerm){
//        List<UserSearchDto> list = userService.findBySearchTerm(searchTerm);
//        if (list!=null) {
//            return new ResponseEntity<List<UserSearchDto>>(list, HttpStatus.OK);
//        }
//        return new ResponseEntity<List<UserSearchDto>>(list, HttpStatus.NOT_FOUND);
//    }
//sabu
    @PostMapping("/followUser")
    public ResponseEntity<Boolean> follow(@RequestBody FollowDto followDto){
        followService.saveFollows(followDto);
        return new ResponseEntity<Boolean>(true,HttpStatus.OK);
    }

//sabu
    @PostMapping("/checkFollow")
    public ResponseEntity<Boolean> checkFollow(@RequestBody FollowDto followDto){
        boolean following = followService.checkFollow(followDto);
        if (following){
            return new ResponseEntity<Boolean>(true,HttpStatus.OK);
        }
        return new ResponseEntity<Boolean>(true,HttpStatus.NOT_FOUND);
    }

//sabu
    @PostMapping("/unfollowUser")
    public ResponseEntity<Boolean> unfollow(@RequestBody FollowDto followDto){
        followService.unfollowUser(followDto);
        return new ResponseEntity<Boolean>(true,HttpStatus.OK);
    }

}
