package com.f1soft.admin.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsersTotalUploadsDto {
    private String userName;
    private String fullName;
    private String profilePhoto;
    private int totalUploads;
}
