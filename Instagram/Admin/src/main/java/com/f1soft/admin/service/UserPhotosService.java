package com.f1soft.admin.service;

import com.f1soft.admin.dto.UserPostDto;
import com.f1soft.admin.dto.UsersTotalUploadsDto;

import java.util.List;

public interface UserPhotosService {
    public List<UsersTotalUploadsDto> getUsersUploadsCount();
    public List<UserPostDto> getUserUploads(String userName);
}
