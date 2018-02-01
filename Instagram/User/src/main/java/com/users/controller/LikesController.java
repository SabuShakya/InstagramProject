package com.users.controller;

import com.users.dto.Commentsdto;
import com.users.dto.Likesdto;
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
    private LikesService likesService;

    @PostMapping("/likeAction")
    public ResponseEntity<Integer> like(@RequestBody Commentsdto commentsdto ){
        int likesCount=likesService.saveLike(commentsdto);
        return new ResponseEntity<Integer>(likesCount, HttpStatus.OK);
    }

    @GetMapping("/likesCount/{imageName}")
    public ResponseEntity<Integer> getLikesCount(@PathVariable("imageName")String imageName){
        int likesCount = likesService.getLikesCountForImage(imageName);
        return new ResponseEntity<Integer>(likesCount,HttpStatus.OK);
    }

    @GetMapping("/getLikesList/{imageName}")
    public ResponseEntity<List<Likesdto>> getLikesList(@PathVariable("imageName")String imageName){
        List<Likesdto> likesdtoList = likesService.getLikesList(imageName);
        return new ResponseEntity<List<Likesdto>>(likesdtoList,HttpStatus.OK);
    }
}
