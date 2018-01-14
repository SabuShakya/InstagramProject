package com.f1soft.admin.service;

import com.f1soft.admin.dto.AdminLoginDto;
import com.f1soft.admin.model.Admin;
import com.f1soft.admin.model.TokenAuth;
import com.f1soft.admin.repository.AdminRepository;
import com.f1soft.admin.utils.TokenUtils;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.List;

@Service
@Transactional("transactionManager")
public class AdminServiceImpl implements AdminService{

    @Resource
    private AdminRepository adminRepository;

    @Autowired
    private TokenAuthService tokenAuthService;

    public boolean loginAdmin(AdminLoginDto adminLoginDto) {
        Admin isAdmin = adminRepository.getAdminByUserName(adminLoginDto.getUserName());
        if((isAdmin !=null) && BCrypt.checkpw(adminLoginDto.getPassword(),isAdmin.getPassword())){
            adminLoginDto.setId(isAdmin.getId());
           return true;
        }
        return false;
    }

    public Admin getAdmin(String userName) {
        Admin adminFromrepo= adminRepository.getAdminByUserName(userName);
        System.out.println(adminFromrepo);
        return adminFromrepo;
    }

    public void createAdmin(Admin admin)
    {
        admin.setPassword(BCrypt.hashpw(admin.getPassword(),BCrypt.gensalt()));
        adminRepository.save(admin);
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

        adminRepository.getAdminByUserName(admin.getUserName());
        admin.setImage(filename);
        adminRepository.save(admin);
    }

    public List<Admin> getAllAdmins() {
        return adminRepository.findAll();
    }

    public void deleteAdmin(Admin admin) {
        adminRepository.delete(admin);
    }


}
