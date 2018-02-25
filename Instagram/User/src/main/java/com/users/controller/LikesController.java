package com.users.controller;

import com.users.dto.Commentsdto;
import com.users.dto.LikeActiondto;
import com.users.dto.Likesdto;
import com.users.model.User;
import com.users.repository.UserRepository;
import com.users.service.LikesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/")
public class LikesController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private LikesService likesService;

    @PostMapping("/likeAction")
    public ResponseEntity<LikeActiondto> like(@RequestBody Commentsdto commentsdto ){
        LikeActiondto likesCount=likesService.saveLike(commentsdto);
        return new ResponseEntity<LikeActiondto>(likesCount, HttpStatus.OK);
    }

    @GetMapping("/likesCount/{imageName}/{userName}")
    public ResponseEntity<LikeActiondto> getLikesCount(@PathVariable("imageName")String imageName,
                                                 @PathVariable("userName")String userName){
        User user = userRepository.getUserByUsername(userName);
        LikeActiondto likesCount = likesService.getLikesCountForImage(imageName,user);
        if(likesCount.getLikeCount()==0){
            return new ResponseEntity<LikeActiondto>(likesCount,HttpStatus.OK);
        }
        return new ResponseEntity<LikeActiondto>(likesCount,HttpStatus.NOT_FOUND);
    }

    @GetMapping("/getLikesList/{imageName}")
    public ResponseEntity<List<Likesdto>> getLikesList(@PathVariable("imageName")String imageName){
        List<Likesdto> likesdtoList = likesService.getLikesList(imageName);
        return new ResponseEntity<List<Likesdto>>(likesdtoList,HttpStatus.OK);
    }
}
