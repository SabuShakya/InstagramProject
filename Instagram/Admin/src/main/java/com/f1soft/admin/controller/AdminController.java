package com.f1soft.admin.controller;

import com.f1soft.admin.dto.AdminInfoDto;
import com.f1soft.admin.dto.AdminLoginDto;
import com.f1soft.admin.dto.TokenAuthDto;
import com.f1soft.admin.model.Admin;
import com.f1soft.admin.model.TokenAuth;
import com.f1soft.admin.service.AdminService;
import com.f1soft.admin.service.TokenAuthService;
import com.f1soft.admin.utils.TokenUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private TokenAuthService tokenAuthService;

    @PostMapping("/login")
    public ResponseEntity<TokenAuthDto> getAdmin(@RequestBody AdminLoginDto adminLoginDto) {
        boolean loginAdmin = adminService.loginAdmin(adminLoginDto);
        TokenAuthDto tokenAuthDto = new TokenAuthDto();
        if (loginAdmin) {
            tokenAuthDto =tokenAuthService.authenticateToken(adminLoginDto);
            return new ResponseEntity<TokenAuthDto>(tokenAuthDto,HttpStatus.OK);
        }
        return new ResponseEntity<TokenAuthDto>(tokenAuthDto,HttpStatus.NOT_FOUND);
    }

    @GetMapping("/getAdminId/{tokenNo}/{userName}")
    public ResponseEntity<AdminInfoDto> getAdminId(@PathVariable("tokenNo") String tokenNo,
                                             @PathVariable("userName") String userName){
        AdminInfoDto adminInfoDto = new AdminInfoDto();
        if (tokenNo!= null){
          boolean loggedin=tokenAuthService.verifyIfLoggedIn(adminService.getAdmin(userName),tokenNo);
            if (loggedin){
                return new ResponseEntity<AdminInfoDto>(adminInfoDto,HttpStatus.OK);
            }
            return new ResponseEntity<AdminInfoDto>(adminInfoDto,HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<AdminInfoDto>(adminInfoDto,HttpStatus.NOT_FOUND);
    }

    @PostMapping("/signup")
    public ResponseEntity<Boolean> saveAdmin(@RequestBody Admin admin){
         adminService.createAdmin(admin);
         tokenAuthService.saveToken(admin);
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
