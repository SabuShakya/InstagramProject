package com.f1soft.admin.controller;

import com.f1soft.admin.dto.*;
import com.f1soft.admin.service.CommentsService;
import com.f1soft.admin.service.UserPhotosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
public class UserPhotoController {

    @Autowired
    private UserPhotosService userPhotosService;

    @Autowired
    private CommentsService commentsService;

    @GetMapping("/usersUploads")
    public ResponseEntity<List<UsersTotalUploadsDto>> getUploadsCount(){
        List<UsersTotalUploadsDto> list = userPhotosService.getUsersUploadsCount();
        return new ResponseEntity<List<UsersTotalUploadsDto>>(list, HttpStatus.OK);
    }

    @GetMapping("/getUploadsOf/{userName}")
    public ResponseEntity<List<UserPostDto>> getUploads(@PathVariable("userName") String userName,
                                                        @RequestParam("page") int page, @RequestParam("size") int size){
        org.springframework.data.domain.Pageable pageable = new PageRequest(page,size);
        List<UserPostDto> posts =userPhotosService.getUserUploads(userName,pageable);
        return new ResponseEntity<List<UserPostDto>>(posts,HttpStatus.OK);
    }

    @GetMapping("/getCommentsOfThisPicture/{imageName}")
    public ResponseEntity<List<Commentsdto>> getComments(@PathVariable("imageName") String imageName){
        List<Commentsdto> commentsdtoList = commentsService.getAllComments(imageName);
        return  new ResponseEntity<List<Commentsdto>>(commentsdtoList,HttpStatus.OK);
    }

    @GetMapping("/getUploadsPerDay")
    public ResponseEntity<List<UserPostDto>> getUploadsPerDay(){
        List<UserPostDto> posts =userPhotosService.getUploadsPerDay();
        return new ResponseEntity<List<UserPostDto>>(posts,HttpStatus.OK);
    }

    @GetMapping("/allPhotos/{username}")
    public ResponseEntity<List<UserPhotodto>> photoList(@PathVariable("username") String username){
        List<UserPhotodto> photoList= userPhotosService.getAllPhotos(username);
        if(photoList != null && !photoList.isEmpty()){
            return new ResponseEntity<List<UserPhotodto>>(photoList,HttpStatus.OK);
        }
        return new ResponseEntity<List<UserPhotodto>>(photoList,HttpStatus.NOT_FOUND);
    }

    @GetMapping("/getProfilePhoto/{username}")
    public ResponseEntity<ProfilePhotoDto> getProfilePhoto(@PathVariable("username") String username){
        ProfilePhotoDto profilePhoto = userPhotosService.getProfilePhoto(username);
        return new ResponseEntity<ProfilePhotoDto>(profilePhoto,HttpStatus.OK);
    }
}
