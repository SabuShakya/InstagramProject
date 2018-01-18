package com.users.repository;

import com.users.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {
    public User getUserByUsername(String username);
    public List<User> findByUsername(String searchTerm);
}
