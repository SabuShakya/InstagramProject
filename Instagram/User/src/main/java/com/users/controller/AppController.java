package com.users.controller;

import com.users.dto.UserPhotodto;
import com.users.dto.Userdto;
import com.users.model.User;
import com.users.model.UserPhotos;
import com.users.model.UserTokenAuth;
import com.users.service.PhotoService;
import com.users.service.UserService;
import com.users.service.UserTokenAuthService;
import org.mindrot.jbcrypt.BCrypt;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.FileOutputStream;
import java.util.Base64;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/")
public class AppController {
    @Autowired
    private UserService userService;

    @Autowired
    private PhotoService photoService;

    @Autowired
    private UserTokenAuthService userTokenAuthService;

    @GetMapping("/allusers")
    public ResponseEntity<List<User>> listAllUsers() {
        List<User> users = userService.findAllUsers();
        HttpHeaders httpHeaders= new HttpHeaders();

        if(users.isEmpty()){
            return new ResponseEntity<List<User>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<User>>(users, httpHeaders, HttpStatus.OK);
    }

    @PostMapping(value = "/signup", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Boolean> createUser(@RequestBody User user) {
        userService.saveUser(user);
        userTokenAuthService.saveToken(user);
        return new ResponseEntity<Boolean>(true, HttpStatus.OK);
    }


    @PostMapping("/login")
    public ResponseEntity<Userdto> getUser(@RequestBody User user){
        userTokenAuthService.authUser(user);

            userService.saveToken(isUser);
            userdto.setUsername(isUser.getUsername());
            userdto.setTokenNo(isUser.getTokenNo());
            return new ResponseEntity<Userdto>(userdto, HttpStatus.OK);
        }
        return new ResponseEntity<Userdto>(userdto, HttpStatus.NOT_FOUND);
    }

   @PostMapping("/upload")
        public ResponseEntity<Boolean> uploads(@RequestBody UserPhotodto userPhotodto){
        photoService.savePhoto(userPhotodto);
        return new ResponseEntity<Boolean>(true,HttpStatus.OK);
   }

    @GetMapping("/allPhotos")
    public ResponseEntity<List<UserPhotos>> photoList(){
        List<UserPhotos> photoList= photoService.getAllPhotos();
        if(photoList != null){
            return new ResponseEntity<List<UserPhotos>>(photoList,HttpStatus.OK);
        }
        return new ResponseEntity<List<UserPhotos>>(photoList,HttpStatus.NOT_FOUND);
    }

//    @GetMapping("/uploads/{tokenNo}/{user_id}")
//    public ResponseEntity<User> getUserID(@PathVariable("tokenNo") String tokenNo,
//                                            @PathVariable("user_id") String user_id){
//        User user = userService.getUserByTokenNo(tokenNo,user_id);
//        if (user != null){
//            return new ResponseEntity<User>(user,HttpStatus.OK);
//        }
//        return new ResponseEntity<User>(user,HttpStatus.NOT_FOUND);
//    }
}
