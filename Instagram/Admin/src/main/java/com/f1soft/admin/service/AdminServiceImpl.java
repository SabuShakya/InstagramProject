package com.f1soft.admin.service;

import com.f1soft.admin.dto.AdminDto;
import com.f1soft.admin.model.Admin;
import com.f1soft.admin.repository.AdminRepository;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.UUID;

@Service
@Transactional("transactionManager")
public class AdminServiceImpl implements AdminService{

//  private static String filepath = "/home/sabu/Downloads/apache-tomcat-8.0.35-windows-x64/apache-tomcat-8.0.35/Images/";

    @Resource
    private AdminRepository adminRepository;

    public Admin getAdmin(String userId) {
        Admin adminFromrepo= adminRepository.getAdminByUserId(userId);
        System.out.println(adminFromrepo);
        return adminFromrepo;
    }

    public void createAdmin(Admin admin)
    {
        admin.setPassword(BCrypt.hashpw(admin.getPassword(),BCrypt.gensalt()));
        adminRepository.save(admin);
    }

    public void saveToken(Admin admin) {
        adminRepository.save(admin);
    }

    public Admin getAdminByTokenNo(String token,String userId) {
        return adminRepository.getAdminByTokenNoAndUserId(token,userId);
    }

    public void updateAdmin(Admin admin) {
        System.out.println(System.getProperty("catalina.home"));
        File dir = new File(System.getProperty("catalina.home")+"/uploads");
        System.out.println(dir);
        if(!dir.exists()){
            dir.mkdir();
        }
        byte[] decodedImage = Base64.getDecoder().decode(admin.getImage());
        String filename = decodedImage + ".jpg";
        String pathToImage = dir +"/"+ filename;
        try {
            FileOutputStream fout = new FileOutputStream(pathToImage);
            fout.write(decodedImage);
            fout.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        adminRepository.getAdminByUserId(admin.getUserId());
        admin.setImage(filename);
        adminRepository.save(admin);
    }

    public List<Admin> getAllAdmins() {
        List<Admin> adminList = adminRepository.findAll();
        System.out.println(adminList.toString());
        return adminList;
    }

    public void deleteAdmin(Admin admin) {
        adminRepository.delete(admin);
    }
}
