package com.f1soft.admin.controller;

import com.f1soft.admin.dto.AdminInfoDto;
import com.f1soft.admin.dto.AdminLoginDto;
import com.f1soft.admin.dto.TokenAuthDto;
import com.f1soft.admin.dto.UserLogsDto;
import com.f1soft.admin.model.Admin;
import com.f1soft.admin.model.TokenAuth;
import com.f1soft.admin.service.AdminService;
import com.f1soft.admin.service.TokenAuthService;
import com.f1soft.admin.service.UserLogService;
import com.f1soft.admin.utils.AdminUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private TokenAuthService tokenAuthService;

    @Autowired
    private UserLogService userLogService;

    @PostMapping("/login")
    public ResponseEntity<TokenAuthDto> getAdmin(@RequestBody AdminLoginDto adminLoginDto) {
        boolean loginAdmin = adminService.loginAdmin(adminLoginDto);
        TokenAuthDto tokenAuthDto = null;
        if (loginAdmin) {
            tokenAuthDto =tokenAuthService.authenticateToken(adminLoginDto);
            return new ResponseEntity<TokenAuthDto>(tokenAuthDto,HttpStatus.OK);
        }
        return new ResponseEntity<TokenAuthDto>(tokenAuthDto,HttpStatus.NOT_FOUND);
    }

    @GetMapping("/getAdminId/{tokenNo}/{userName}")
    public ResponseEntity<Boolean> getAdminId(@PathVariable("tokenNo") String tokenNo,
                                             @PathVariable("userName") String userName){
        AdminInfoDto adminInfoDto = new AdminInfoDto();
        if (tokenNo!= null){
          boolean loggedin=tokenAuthService.verifyIfLoggedIn(adminService.getAdmin(userName),tokenNo);
            if (loggedin){
                return new ResponseEntity<Boolean>(true,HttpStatus.OK);
            }
        }
        return new ResponseEntity<Boolean>(false,HttpStatus.NOT_FOUND);
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logoutAdmin(@RequestBody TokenAuthDto tokenAuthDto){
        Admin admin = adminService.getAdmin(tokenAuthDto.getUserName());
        tokenAuthService.logoutAdmin(admin.getId(),tokenAuthDto.getTokenNo());
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @PostMapping("/signup")
    public ResponseEntity<Boolean> saveAdmin(@RequestBody Admin admin){
         adminService.createAdmin(admin);
         tokenAuthService.saveToken(admin);
         return new ResponseEntity<Boolean>(true,HttpStatus.OK);
    }

    @PostMapping("/update")
    public ResponseEntity<Boolean> updateAdmin(@RequestBody AdminInfoDto adminInfoDto){
        adminService.updateAdmin(adminInfoDto);
        return new ResponseEntity<Boolean>(true,HttpStatus.OK);
    }

    @GetMapping("/getAllAdmins")
    public ResponseEntity<List<AdminInfoDto>> viewLog(){
        List<Admin> adminList = adminService.getAllAdmins();
        List<AdminInfoDto> list =  new ArrayList<AdminInfoDto>();
        if (adminList != null) {
            list = AdminUtils.convertAdminListToAdminInfoDtoList(adminList);
            return new ResponseEntity<List<AdminInfoDto>>(list,HttpStatus.OK);
        }
        return new ResponseEntity<List<AdminInfoDto>>(list,HttpStatus.NOT_FOUND);
    }

    @PostMapping("/deleteAdmin")
    public ResponseEntity<Boolean> deleteAdmin(@RequestBody Admin admin){
        adminService.deleteAdmin(admin);
        return new ResponseEntity<Boolean>(true,HttpStatus.OK);
    }

    @GetMapping("/getUserLogs")
    public ResponseEntity<UserLogsDto> getLogs(){
        UserLogsDto userLogsDto = userLogService.getUserLogs();
        return new ResponseEntity<UserLogsDto>(userLogsDto,HttpStatus.OK);
    }
}
