package com.users.controller;

import com.users.dto.UserPhotodto;
import com.users.dto.Userdto;
import com.users.model.User;

//import com.users.service.PhotoService;
import com.users.model.UserPhotos;
import com.users.service.PhotoService;
import com.users.service.UserService;
import org.mindrot.jbcrypt.BCrypt;
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

@RestController
@RequestMapping("/")
public class AppController {
    @Autowired
    private UserService userService;

    @Autowired
    private PhotoService photoService;

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
        return new ResponseEntity<Boolean>(true, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<Userdto> getUser(@RequestBody Userdto userdto){

        User isUser = userService.getUser(userdto.getUname());
        System.out.println(isUser);
        if((isUser != null)&& (BCrypt.checkpw(userdto.getPassword(),isUser.getPassword()))){
            userdto.setUname(isUser.getUname());
            userdto.setTokenNo(isUser.getTokenNo());
            return new ResponseEntity<Userdto>(userdto,HttpStatus.OK);
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

//    @GetMapping("/getUserId/{tokenNo}/{uname}")
//    public ResponseEntity<User> getUserId(@PathVariable("tokenNo") String tokenNo,
//                                            @PathVariable("uanme") String uname){
//        User admin = userService.getUserByTokenNo(tokenNo,uname);
//        if (admin != null){
//            return new ResponseEntity<User>(admin,HttpStatus.OK);
//        }
//        return new ResponseEntity<User>(admin,HttpStatus.NOT_FOUND);
//    }
}
