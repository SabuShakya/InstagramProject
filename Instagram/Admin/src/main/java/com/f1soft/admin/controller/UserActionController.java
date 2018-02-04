package com.f1soft.admin.controller;

import com.f1soft.admin.dto.UserListInfoDto;
import com.f1soft.admin.dto.UserLogsDto;
import com.f1soft.admin.dto.UsersTotalUploadsDto;
import com.f1soft.admin.service.UserLogService;
import com.f1soft.admin.service.UserPhotosService;
import com.f1soft.admin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/")
public class UserActionController {
    @Autowired
    private UserLogService userLogService;

    @Autowired
    private UserService userService;

    @GetMapping("/getUserLogs")
    public ResponseEntity<UserLogsDto> getUserLog(){
        UserLogsDto userLogsDto = userLogService.getUserLogs();
        return new ResponseEntity<UserLogsDto>(userLogsDto, HttpStatus.OK);
    }
    @GetMapping("/getTotalUsers")
    public ResponseEntity<List<UserListInfoDto>> getTotalUsers(@RequestParam("page") int page,
                                                               @RequestParam("size") int size){
        Pageable pageable = new PageRequest(page,size);
        List<UserListInfoDto> userListInfoDtoList = userService.getTotalUsers(pageable);
        return new ResponseEntity<List<UserListInfoDto>>(userListInfoDtoList,HttpStatus.OK);
    }
    @GetMapping("/getTotalActiveUsers")
    public ResponseEntity<List<UserListInfoDto>> getTotalActiveUsers(@RequestParam("page") int page,
                                                               @RequestParam("size") int size){
        Pageable pageable = new PageRequest(page,size);
        List<UserListInfoDto> userListInfoDtoList = userService.getTotalActiveUsers(pageable);
        return new ResponseEntity<List<UserListInfoDto>>(userListInfoDtoList,HttpStatus.OK);
    }
}
