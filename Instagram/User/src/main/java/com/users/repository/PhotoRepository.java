package com.users.repository;

import com.users.model.UserPhotos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface PhotoRepository extends JpaRepository<UserPhotos, Long> {

    @Query("select u from UserPhotos u where u.created_date =:date")
    public List<UserPhotos> getUploadsPerDay(@Param("date") Date date);

    public List<UserPhotos> getUserPhotosByUserUsername(String username);

    public List<UserPhotos> getUserPhotosByUser_Id(long id);

    @Query("SELECT u from UserPhotos u where u.image_path=:image_path")
    public UserPhotos getUserPhotosByImage_path(@Param("image_path") String image_path);

}
