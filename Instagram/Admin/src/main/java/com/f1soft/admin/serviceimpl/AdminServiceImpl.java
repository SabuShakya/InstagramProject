package com.f1soft.admin.serviceimpl;

import com.f1soft.admin.dto.AdminInfoDto;
import com.f1soft.admin.dto.AdminLoginDto;
import com.f1soft.admin.model.Admin;
import com.f1soft.admin.model.TokenAuth;
import com.f1soft.admin.repository.AdminRepository;
import com.f1soft.admin.service.AdminService;
import com.f1soft.admin.service.TokenAuthService;
import com.f1soft.admin.utils.AdminUtils;
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


@Service
@Transactional("transactionManager")
public class AdminServiceImpl implements AdminService {

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

    @Override
    public AdminInfoDto getAdminPhoto(String userName) {
        Admin admin=adminRepository.getAdminByUserName(userName);
        return AdminUtils.convertAdminToAdminDto(admin);
    }

    public Admin getAdmin(String userName) {
        Admin adminFromrepo= adminRepository.getAdminByUserName(userName);
        System.out.println(adminFromrepo);
        return adminFromrepo;
    }

    public Admin getAdminId(int id) {
        return adminRepository.getAdminById(id);
    }

    public void createAdmin(Admin admin)
    {
        admin.setPassword(BCrypt.hashpw(admin.getPassword(),BCrypt.gensalt()));
        adminRepository.save(admin);
    }

    public void updateAdmin(AdminInfoDto adminInfoDto) {
        if(adminInfoDto.getImage()!=null) {
            File dir = new File(System.getProperty("catalina.home") + "/uploads");
            System.out.println(dir);
            if (!dir.exists()) {
                dir.mkdir();
            }
            byte[] decodedImage = Base64.getDecoder().decode(adminInfoDto.getImage());
            String filename = decodedImage.toString();
            String pathToImage = dir + "/" + filename;
            try {
                FileOutputStream fout = new FileOutputStream(pathToImage);
                fout.write(decodedImage);
                fout.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Admin adminfromRepo = adminRepository.getAdminById(adminInfoDto.getId());
            adminfromRepo.setEmail(adminInfoDto.getEmail());
            adminfromRepo.setName(adminInfoDto.getName());
            adminfromRepo.setUserName(adminInfoDto.getUserName());
            adminfromRepo.setImage(filename);
            adminRepository.save(adminfromRepo);
            }
        Admin adminfromRepo = adminRepository.getAdminById(adminInfoDto.getId());
        adminfromRepo.setEmail(adminInfoDto.getEmail());
        adminfromRepo.setName(adminInfoDto.getName());
        adminfromRepo.setUserName(adminInfoDto.getUserName());
        adminRepository.save(adminfromRepo);
    }

    public java.util.List<Admin> getAllAdmins() {
        java.util.List<Admin> list = adminRepository.findAll();
        return list;
    }

    public void deleteAdmin(Admin admin) {
        adminRepository.delete(adminRepository.getAdminByUserName(admin.getUserName()));
    }
}
