package com.f1soft.admin.service;

import com.f1soft.admin.dto.UserListInfoDto;
import com.f1soft.admin.model.User;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserService {
    public List<UserListInfoDto> getTotalUsers(Pageable pageable);
    public List<UserListInfoDto> getTotalActiveUsers(Pageable pageable);
}
