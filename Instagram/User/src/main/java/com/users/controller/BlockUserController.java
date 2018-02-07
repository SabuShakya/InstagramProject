package com.users.controller;

import com.users.dto.BlockUserdto;
import com.users.service.BlockService;
import com.users.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class BlockUserController {

    @Autowired
    private UserService userService;

    @Autowired
    private BlockService blockService;

    @PostMapping("/blockUser")
    public ResponseEntity<Boolean> blockUser(@RequestBody BlockUserdto blockdto){
        blockService.blockUser(blockdto);
        return new ResponseEntity<Boolean>(true, HttpStatus.OK);
    }

   @PostMapping("/unblockUser")
   public ResponseEntity<Boolean> unblockUser(@RequestBody BlockUserdto blockdto){
       blockService.unblockUser(blockdto);
       return new ResponseEntity<Boolean>(true, HttpStatus.OK);
   }

   @PostMapping("/checkBlock")
   public ResponseEntity<Boolean> checkBlockedUser(@RequestBody BlockUserdto blockdto){
       boolean isblocked= blockService.checkBlocked(blockdto);
       if(isblocked){
           return new ResponseEntity<Boolean>(true, HttpStatus.OK);
       }
       return new ResponseEntity<Boolean>(false,HttpStatus.NOT_FOUND);
   }
}
