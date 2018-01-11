package com.users.repository;

import com.users.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

    public User getUserByUname(String uname);
    public User getUserById(int id);

//    public User findByEmail(String email);
//    public User findByConfirmationToken(String confirmationToken);

    public User getUserByTokenNoAndUname(String tokenNo,String uname);

}
