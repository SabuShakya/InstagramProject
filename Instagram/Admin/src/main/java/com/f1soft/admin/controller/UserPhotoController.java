package com.f1soft.admin.controller;

import com.f1soft.admin.dto.Commentsdto;
import com.f1soft.admin.dto.UserPostDto;
import com.f1soft.admin.dto.UsersTotalUploadsDto;
import com.f1soft.admin.service.CommentsService;
import com.f1soft.admin.service.UserPhotosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseEntity<List<UserPostDto>> getUploads(@PathVariable("userName") String userName){
        List<UserPostDto> posts =userPhotosService.getUserUploads(userName);
        return new ResponseEntity<List<UserPostDto>>(posts,HttpStatus.OK);
    }

    @GetMapping("/getCommentsOfThisPicture/{imageName}")
    public ResponseEntity<List<Commentsdto>> getComments(@PathVariable("imageName") String imageName){
        List<Commentsdto> commentsdtoList = commentsService.getComments(imageName);
        return  new ResponseEntity<List<Commentsdto>>(commentsdtoList,HttpStatus.OK);
    }
}
