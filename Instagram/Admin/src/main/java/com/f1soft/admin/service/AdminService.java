package com.f1soft.admin.service;

import com.f1soft.admin.dto.AdminDto;
import com.f1soft.admin.model.Admin;

import java.util.List;

public interface AdminService {
    public Admin getAdmin(String userId);
    public void createAdmin(Admin admin);
    public void saveToken(Admin admin);
    public Admin getAdminByTokenNo(String token,String userId);
    public void updateAdmin(Admin admin);
    public List<Admin> getAllAdmins();
    public void deleteAdmin(Admin admin);
}
