package com.f1soft.admin.controller;

import com.f1soft.admin.dto.AdminDto;
import com.f1soft.admin.model.Admin;
import com.f1soft.admin.service.AdminService;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @PostMapping("/login")
    public ResponseEntity<AdminDto> getAdmin(@RequestBody AdminDto adminDto){
       Admin isAdmin=adminService.getAdmin(adminDto.getUserId());
       System.out.println(isAdmin);
       if((isAdmin !=null)&& BCrypt.checkpw(adminDto.getPassword(),isAdmin.getPassword())){
           if (isAdmin.getTokenNo()== null){
               String token = UUID.randomUUID().toString().replace("-","");
               System.out.println(token);
               isAdmin.setTokenNo(token);
               adminService.saveToken(isAdmin);
               adminDto.setUserId(isAdmin.getUserId());
               adminDto.setToken(isAdmin.getTokenNo());
           }
           adminDto.setUserId(isAdmin.getUserId());
           adminDto.setToken(isAdmin.getTokenNo());
           return new ResponseEntity<AdminDto>(adminDto,HttpStatus.OK);
       }
        return new ResponseEntity<AdminDto>(adminDto, HttpStatus.NOT_FOUND);
    }
    @GetMapping("/getAdminId/{tokenNo}/{userId}")
    public ResponseEntity<Admin> getAdminId(@PathVariable("tokenNo") String tokenNo,
                                             @PathVariable("userId") String userId){
        Admin admin = adminService.getAdminByTokenNo(tokenNo,userId);
        if (admin != null){
            return new ResponseEntity<Admin>(admin,HttpStatus.OK);
        }
        return new ResponseEntity<Admin>(admin,HttpStatus.NOT_FOUND);
    }
    @PostMapping("/signup")
    public ResponseEntity<Boolean> saveAdmin(@RequestBody Admin admin){
         adminService.createAdmin(admin);
         return new ResponseEntity<Boolean>(true,HttpStatus.OK);
    }
    @PostMapping("/update")
    public ResponseEntity<Boolean> updateAdmin(@RequestBody Admin admin){
        adminService.updateAdmin(admin);
        return new ResponseEntity<Boolean>(true,HttpStatus.OK);
    }
    @GetMapping("/getAllAdmins")
    public ResponseEntity<List<Admin>> viewLog(){
        List<Admin> adminList=adminService.getAllAdmins();
        if(adminList != null){
        return new ResponseEntity<List<Admin>>(adminList,HttpStatus.OK);
        }
        return new ResponseEntity<List<Admin>>(adminList,HttpStatus.NOT_FOUND);
    }
    @PostMapping("/deleteAdmin")
    public ResponseEntity<Boolean> deleteAdmin(@RequestBody Admin admin){
        adminService.deleteAdmin(admin);
        return new ResponseEntity<Boolean>(true,HttpStatus.OK);
    }
}
