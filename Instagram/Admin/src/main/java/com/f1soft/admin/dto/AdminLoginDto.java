package com.f1soft.admin.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class AdminLoginDto implements Serializable{
    private int id;
    private String userName;
    private String password;
}
