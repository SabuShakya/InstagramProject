package com.users.repository;

import com.users.dto.UserPhotodto;
import com.users.model.UserPhotos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PhotoRepository extends JpaRepository<UserPhotos, Long> {

    public List<UserPhotos> getUserPhotosByUserUsername(String username);

    @Query("SELECT u from UserPhotos u where u.image_path=:image_path")
    public UserPhotos getUserPhotosByImage_path(@Param("image_path") String image_path);
}
