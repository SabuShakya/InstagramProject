package com.users.controller;

import com.users.dto.Commentsdto;
import com.users.service.CommentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
public class CommentsController {
    @Autowired
    private CommentsService commentsService;

    @PostMapping("/addComment")
    public ResponseEntity<Boolean> addComment(@RequestBody Commentsdto commentsdto ){
        commentsService.saveComments(commentsdto);
        return new ResponseEntity<Boolean>(true, HttpStatus.OK);
    }

    @GetMapping(value = "/showComments/{image_path}")
    public ResponseEntity<List<Commentsdto>> commentList(@PathVariable("image_path")String image_path){
        List<Commentsdto> commentsdtoList=commentsService.getAllComments(image_path);
        if(commentsdtoList!=null){
            return new ResponseEntity<List<Commentsdto>>(commentsdtoList,HttpStatus.OK);
        }
        return new ResponseEntity<List<Commentsdto>>(commentsdtoList,HttpStatus.NO_CONTENT);
    }

    @PostMapping("/deleteComment")
    public ResponseEntity<Boolean> deleteComment(@RequestBody Commentsdto commentsdto){
        commentsService.deleteComment(commentsdto);
        return new ResponseEntity<Boolean>(true,HttpStatus.OK);
    }

    @PostMapping("/editComment")
    public ResponseEntity<Boolean> editComment(@RequestBody Commentsdto commentsdto){
        commentsService.updateComment(commentsdto);
        return new ResponseEntity<Boolean>(true,HttpStatus.OK);
    }
}
