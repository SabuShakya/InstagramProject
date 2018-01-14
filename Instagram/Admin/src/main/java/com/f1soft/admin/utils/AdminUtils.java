package com.f1soft.admin.utils;

import com.f1soft.admin.dto.AdminLoginDto;
import com.f1soft.admin.model.Admin;

public class AdminUtils {

    public static Admin convertAdminLoginDTOToAdmin(AdminLoginDto adminLoginDTO){
        Admin admin  = new Admin();
        return admin;
    }
}
