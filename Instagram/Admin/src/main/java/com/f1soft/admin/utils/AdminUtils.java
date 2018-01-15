package com.f1soft.admin.utils;

import com.f1soft.admin.dto.AdminInfoDto;
import com.f1soft.admin.dto.AdminLoginDto;
import com.f1soft.admin.model.Admin;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

public class AdminUtils {

    public static Admin convertAdminLoginDTOToAdmin(AdminLoginDto adminLoginDTO){
        Admin admin  = new Admin();
        return admin;
    }

    public static List<AdminInfoDto> convertAdminListToAdminInfoDtoList(List<Admin> adminList){
        List<AdminInfoDto> adminInfoDtoList = new ArrayList<AdminInfoDto>();
        ModelMapper modelMapper = new ModelMapper();
        for(Admin admin:adminList){
            AdminInfoDto mapAdmin = modelMapper.map(admin, AdminInfoDto.class);
            adminInfoDtoList.add(mapAdmin);
            System.out.println(adminInfoDtoList);
        }
        return adminInfoDtoList;
    }
}
