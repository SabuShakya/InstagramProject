package com.f1soft.admin.repository;

import com.f1soft.admin.model.UserPhotos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface PhotoRepository extends JpaRepository<UserPhotos, Long> {

    @Query("select u from UserPhotos u where u.created_date =:date")
    public List<UserPhotos> getUploadsPerDay(@Param("date") Date date);
}
