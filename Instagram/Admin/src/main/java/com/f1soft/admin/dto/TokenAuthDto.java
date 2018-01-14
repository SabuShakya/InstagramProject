package com.f1soft.admin.dto;

import com.f1soft.admin.model.Admin;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TokenAuthDto {
//    private Admin admin;
    private String name;
    private String userName;
    private String tokenNo;
    private Character status;
}
