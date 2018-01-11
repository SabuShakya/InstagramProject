package com.users.repository;

import com.users.dto.UserPhotodto;
import com.users.model.UserPhotos;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhotoRepository extends JpaRepository<UserPhotos, Long> {

}
