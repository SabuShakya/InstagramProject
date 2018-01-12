package com.f1soft.admin.repository;

import com.f1soft.admin.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin,Integer> {
    public Admin getAdminByUserName(String username);
}
