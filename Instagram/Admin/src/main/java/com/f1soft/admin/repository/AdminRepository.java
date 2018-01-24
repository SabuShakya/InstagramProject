package com.f1soft.admin.repository;

import com.f1soft.admin.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface AdminRepository extends JpaRepository<Admin,Integer> ,PagingAndSortingRepository<Admin,Integer> {
    public Admin getAdminById(int id);
    public Admin getAdminByUserName(String username);
}
