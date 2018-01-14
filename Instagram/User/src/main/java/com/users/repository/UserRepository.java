package com.users.repository;

import com.users.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

    public User getUserByUsername(String username);

//    public User getUserByTokenNoAndUsername(String tokenNo,String username);

}
