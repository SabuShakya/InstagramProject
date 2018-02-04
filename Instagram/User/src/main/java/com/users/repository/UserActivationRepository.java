package com.users.repository;

import com.users.model.UserActivation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserActivationRepository extends JpaRepository<UserActivation, Long> {
    public UserActivation getByUserUsername(String username);

    public UserActivation findByUser_Username(String username);
}
