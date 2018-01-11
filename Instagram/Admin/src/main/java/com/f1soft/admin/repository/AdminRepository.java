package com.f1soft.admin.repository;

import com.f1soft.admin.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin,Integer> {
    public Admin getAdminById(int id);
    public Admin getAdminByUserId(String userId);
    public Admin getAdminByTokenNoAndUserId(String tokenNo,String userId);
}
