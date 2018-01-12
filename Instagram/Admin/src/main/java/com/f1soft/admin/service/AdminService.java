package com.f1soft.admin.service;


import com.f1soft.admin.dto.AdminLoginDto;
import com.f1soft.admin.model.Admin;
import com.f1soft.admin.model.TokenAuth;

import java.util.List;

public interface AdminService {
    public Admin getAdmin(String userName);
    public void createAdmin(Admin admin);
    public void updateAdmin(Admin admin);
    public List<Admin> getAllAdmins();
    public void deleteAdmin(Admin admin);
    public boolean loginAdmin(AdminLoginDto adminLoginDto);
}
