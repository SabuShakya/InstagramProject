package com.users.controller;

import com.users.dto.*;
import com.users.service.FollowService;
import com.users.service.PhotoService;
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
    private PhotoService photoService;

    @PostMapping("/followUser")
    public ResponseEntity<Boolean> follow(@RequestBody FollowDto followDto) {
        followService.saveFollows(followDto);
        return new ResponseEntity<Boolean>(true, HttpStatus.OK);
    }

    @PostMapping("/checkFollow")
    public ResponseEntity<Boolean> checkFollow(@RequestBody FollowDto followDto) {
        boolean following = followService.checkFollow(followDto);
        if (following) {
            return new ResponseEntity<Boolean>(true, HttpStatus.OK);
        }
        return new ResponseEntity<Boolean>(false, HttpStatus.NOT_FOUND);
    }

    @PostMapping("/unfollowUser")
    public ResponseEntity<Boolean> unfollow(@RequestBody FollowDto followDto) {
        followService.unfollowUser(followDto);
        return new ResponseEntity<Boolean>(true, HttpStatus.OK);
    }

//    @GetMapping("/followsCount/{username}")
//    public ResponseEntity<FollowCountDto> getFollowCount(@PathVariable("username")String username){
//        FollowCountDto followCountDto = followService.getFollowCount(username);
//        followCountDto.setTotalPictures(photoService.getPhotoCount(username));
//        return new ResponseEntity<FollowCountDto>(followCountDto,HttpStatus.OK);
//    }

    @GetMapping("/followersCount/{username}")
    public ResponseEntity<FollowersCountdto> getFollowersCount(@PathVariable("username") String username) {
        FollowersCountdto followersCountDto = followService.getFollowersCount(username);
        followersCountDto.setTotalPictures(photoService.getPhotoCount(username));
        if (followersCountDto.getFollowers() == (0)) {
            return new ResponseEntity<FollowersCountdto>(followersCountDto, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<FollowersCountdto>(followersCountDto, HttpStatus.OK);
    }

    @GetMapping("/followingCount/{username}")
    public ResponseEntity<FollowingCountdto> getFollowingCount(@PathVariable("username") String username) {
        FollowingCountdto followCountDto = followService.getFollowingCount(username);
        if (followCountDto.getFollowing() == (0)) {
            return new ResponseEntity<FollowingCountdto>(followCountDto, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<FollowingCountdto>(followCountDto, HttpStatus.OK);
    }


    @GetMapping("/getFollowersList/{username}/{loggedInUserName}")
    public ResponseEntity<List<UserSearchDto>> getFollowerList(@PathVariable("username") String username,
                                                               @PathVariable("loggedInUserName") String loggedInUserName) {
        List<UserSearchDto> followDtoList = followService.getFollowersList(username,loggedInUserName);
        return new ResponseEntity<List<UserSearchDto>>(followDtoList, HttpStatus.OK);
    }

    @GetMapping("/getFollowingList/{username}/{loggedInUserName}")
    public ResponseEntity<List<UserSearchDto>> getFollowingList(@PathVariable("username") String username,
                                                                @PathVariable("loggedInUserName") String loggedInUserName) {
        List<UserSearchDto> followDtoList = followService.getFollowingList(username,loggedInUserName);
        return new ResponseEntity<List<UserSearchDto>>(followDtoList, HttpStatus.OK);
    }
}
