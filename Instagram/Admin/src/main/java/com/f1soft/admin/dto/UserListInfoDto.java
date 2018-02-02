package com.f1soft.admin.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserListInfoDto {
    private Long userId;
    private String username;
    private String fullname;
    private String imagename;
    private int totalItems;
}
