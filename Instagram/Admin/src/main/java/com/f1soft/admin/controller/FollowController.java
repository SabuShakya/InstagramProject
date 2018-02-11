package com.f1soft.admin.controller;

import com.f1soft.admin.dto.FollowCountDto;
import com.f1soft.admin.dto.FollowDto;
import com.f1soft.admin.service.FollowService;
import com.f1soft.admin.service.UserPhotosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
public class FollowController {
    @Autowired
    private FollowService followService;

    @Autowired
    private UserPhotosService photoService;

    @GetMapping("/followsCount/{username}")
    public ResponseEntity<FollowCountDto> getFollowCount(@PathVariable("username")String username){
        FollowCountDto followCountDto = followService.getFollowCount(username);
        followCountDto.setTotalPictures(photoService.getPhotoCount(username));
        return new ResponseEntity<FollowCountDto>(followCountDto,HttpStatus.OK);
    }

    @GetMapping("/getFollowersList/{username}")
    public ResponseEntity<List<FollowDto>> getFollowerList(@PathVariable("username")String username){
        List<FollowDto> followDtoList = followService.getFollowersList(username);
        return new ResponseEntity<List<FollowDto>>(followDtoList,HttpStatus.OK);
    }

    @GetMapping("/getFollowingList/{username}")
    public ResponseEntity<List<FollowDto>> getFollowingList(@PathVariable("username")String username){
        List<FollowDto> followDtoList = followService.getFollowingList(username);
        return new ResponseEntity<List<FollowDto>>(followDtoList,HttpStatus.OK);
    }
}
