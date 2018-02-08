package com.f1soft.admin.service;

import com.f1soft.admin.dto.UserPostDto;
import com.f1soft.admin.dto.UsersTotalUploadsDto;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserPhotosService {
    public List<UsersTotalUploadsDto> getUsersUploadsCount();
    public List<UserPostDto> getUserUploads(String userName , Pageable pageable);
    public List<UserPostDto> getUploadsPerDay();

}
