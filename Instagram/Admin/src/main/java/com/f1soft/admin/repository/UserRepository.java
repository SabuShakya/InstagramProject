package com.f1soft.admin.repository;

import com.f1soft.admin.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User,Long> {
    public User getUserByUsername(String username);
    public User getUserByEmail(String email);
    public List<User> findByUsername(String searchTerm);
}
