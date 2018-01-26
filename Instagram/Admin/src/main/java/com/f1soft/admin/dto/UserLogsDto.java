package com.f1soft.admin.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserLogsDto {
    private int totalUsers;
    private int activeUsers;
    private int totalUploads;
    private int uploadsPerDay;
}
