package com.users.controller;

import com.users.dto.CaptionDto;
import com.users.dto.ProfilePhotoDto;
import com.users.dto.UserPhotodto;
import com.users.dto.Userdto;
import com.users.model.User;
import com.users.model.UserPhotos;
import com.users.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
public class PhotoController {
    @Autowired
    private UserService userService;

    @Autowired
    private PhotoService photoService;

    @Autowired
    private ProfilePhotoService profilePhotoService;

    @Autowired
    private FollowService followService;

    @PostMapping("/upload")
    public ResponseEntity<Boolean> uploads(@RequestBody UserPhotodto userPhotodto) {
        photoService.savePhoto(userPhotodto);
        return new ResponseEntity<Boolean>(true, HttpStatus.OK);
    }

    @GetMapping("/allPhotos/{username}")
    public ResponseEntity<List<UserPhotodto>> photoList(@PathVariable("username") String username) {
        List<UserPhotodto> photoList = photoService.getAllPhotos(username);
        if (photoList != null && !photoList.isEmpty()) {
            return new ResponseEntity<List<UserPhotodto>>(photoList, HttpStatus.OK);
        }
        return new ResponseEntity<List<UserPhotodto>>(photoList, HttpStatus.NOT_FOUND);
    }

    @PostMapping("/uploadProfilePhoto")
    public ResponseEntity<Boolean> uploadPhoto(@RequestBody ProfilePhotoDto profilePhotoDto) {
        profilePhotoService.savePhoto(profilePhotoDto);
        return new ResponseEntity<Boolean>(true, HttpStatus.OK);
    }

    @GetMapping("/getProfilePhoto/{username}")
    public ResponseEntity<ProfilePhotoDto> getProfilePhoto(@PathVariable("username") String username) {
        ProfilePhotoDto profilePhoto = profilePhotoService.getProfilePhoto(username);
        return new ResponseEntity<ProfilePhotoDto>(profilePhoto, HttpStatus.OK);
    }

    @GetMapping("/searchedUserPhotos/{username}")
    public ResponseEntity<List<UserPhotodto>> photos(@PathVariable("username") String username) {
        User user = userService.getUser(username);
        Userdto userdto = new Userdto();
        userdto.setUsername(user.getUsername());
        List<UserPhotodto> photoList = photoService.getAllPhotos(username);
        return new ResponseEntity<List<UserPhotodto>>(photoList, HttpStatus.OK);
    }

    @GetMapping("/userStatusPhotos/{username}")
    public ResponseEntity<List<UserPhotodto>> userStatusPhoto(@PathVariable("username") String username) {
        User user = userService.getUser(username);
        Userdto userdto = new Userdto();
        userdto.setUsername(user.getUsername());
        List<UserPhotodto> photoList = photoService.getAllPhotos(username);
        boolean isPublic = userService.checkAccountStatus(username);
        if (isPublic) {
            return new ResponseEntity<List<UserPhotodto>>(photoList, HttpStatus.OK);
        }
        return new ResponseEntity<List<UserPhotodto>>(photoList, HttpStatus.NOT_FOUND);
    }

    @PostMapping("/deletePhoto")
    public ResponseEntity<Boolean> delete(@RequestBody UserPhotos userPhotos) {
        photoService.deletePhoto(userPhotos);
        return new ResponseEntity<Boolean>(true, HttpStatus.OK);
    }

    @PostMapping("/editCaption")
    public ResponseEntity<Boolean> editCaption(@RequestBody UserPhotodto userPhotodto) {
        photoService.updateCaption(userPhotodto);
        return new ResponseEntity<Boolean>(true, HttpStatus.OK);
    }

    @GetMapping("/getCaptionOf/{imageName}")
    public ResponseEntity<CaptionDto> getCaption(@PathVariable("imageName") String imageName) {
        CaptionDto caption = new CaptionDto();
        caption.setCaption(photoService.getCaption(imageName));
        return new ResponseEntity<CaptionDto>(caption, HttpStatus.OK);
    }

}
