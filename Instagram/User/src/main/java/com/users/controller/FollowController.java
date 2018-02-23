package com.users.controller;

import com.users.dto.FollowCountDto;
import com.users.dto.FollowDto;
import com.users.dto.UserSearchDto;
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
    public ResponseEntity<Boolean> follow(@RequestBody FollowDto followDto){
        followService.saveFollows(followDto);
        return new ResponseEntity<Boolean>(true, HttpStatus.OK);
    }

    @PostMapping("/checkFollow")
    public ResponseEntity<Boolean> checkFollow(@RequestBody FollowDto followDto){
        boolean following = followService.checkFollow(followDto);
        if (following){
            return new ResponseEntity<Boolean>(true,HttpStatus.OK);
        }
        return new ResponseEntity<Boolean>(false,HttpStatus.NOT_FOUND);
    }

    @PostMapping("/unfollowUser")
    public ResponseEntity<Boolean> unfollow(@RequestBody FollowDto followDto){
        followService.unfollowUser(followDto);
        return new ResponseEntity<Boolean>(true,HttpStatus.OK);
    }

    @GetMapping("/followsCount/{username}")
    public ResponseEntity<FollowCountDto> getFollowCount(@PathVariable("username")String username){
        FollowCountDto followCountDto = followService.getFollowCount(username);
        followCountDto.setTotalPictures(photoService.getPhotoCount(username));
        return new ResponseEntity<FollowCountDto>(followCountDto,HttpStatus.OK);
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
