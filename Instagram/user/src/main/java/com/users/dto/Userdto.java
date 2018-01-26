package com.users.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class Userdto implements Serializable{
    private long id;
    private String fullName;
    private String username;
    private String password;
}
