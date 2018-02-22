package com.f1soft.admin.controller;

import com.f1soft.admin.dto.Commentsdto;
import com.f1soft.admin.service.CommentsService;
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

    @GetMapping(value = "/showComments/{image_path}")
    public ResponseEntity<List<Commentsdto>> commentList(@PathVariable("image_path")String image_path){
        List<Commentsdto> commentsdtoList=commentsService.getAllComments(image_path);
        if(commentsdtoList!=null){
            return new ResponseEntity<List<Commentsdto>>(commentsdtoList,HttpStatus.OK);
        }
        return new ResponseEntity<List<Commentsdto>>(commentsdtoList,HttpStatus.NO_CONTENT);
    }

}
