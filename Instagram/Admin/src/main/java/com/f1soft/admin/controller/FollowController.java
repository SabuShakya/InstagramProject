package com.f1soft.admin.controller;

import com.f1soft.admin.dto.*;
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

    @GetMapping("/followersCount/{username}")
    public ResponseEntity<FollowersCountdto> getFollowersCount(@PathVariable("username")String username){
        FollowersCountdto followersCountDto = followService.getFollowersCount(username);
        followersCountDto.setTotalPictures(photoService.getPhotoCount(username));
        if(followersCountDto.getFollowers()==(0)) {
            return new ResponseEntity<FollowersCountdto>(followersCountDto, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<FollowersCountdto>(followersCountDto, HttpStatus.OK);
    }

    @GetMapping("/followingCount/{username}")
    public ResponseEntity<FollowingCountdto> getFollowingCount(@PathVariable("username")String username){
        FollowingCountdto followCountDto = followService.getFollowingCount(username);
        if(followCountDto.getFollowing()==(0)) {
            return new ResponseEntity<FollowingCountdto>(followCountDto, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<FollowingCountdto>(followCountDto, HttpStatus.OK);
    }

    @GetMapping("/getFollowersList/{username}")
    public ResponseEntity<List<UserSearchDto>> getFollowerList(@PathVariable("username")String username){
        List<UserSearchDto> followDtoList = followService.getFollowersList(username);
        return new ResponseEntity<List<UserSearchDto>>(followDtoList,HttpStatus.OK);
    }

    @GetMapping("/getFollowingList/{username}")
    public ResponseEntity<List<UserSearchDto>> getFollowingList(@PathVariable("username")String username){
        List<UserSearchDto> followDtoList = followService.getFollowingList(username);
        return new ResponseEntity<List<UserSearchDto>>(followDtoList,HttpStatus.OK);
    }
}
