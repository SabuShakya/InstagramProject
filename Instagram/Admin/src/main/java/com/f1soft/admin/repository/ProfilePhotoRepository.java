package com.f1soft.admin.repository;


import com.f1soft.admin.model.ProfilePhoto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProfilePhotoRepository extends JpaRepository<ProfilePhoto,Long> {

    @Query("SELECT p from ProfilePhoto p where p.user.username=:username" )
    public ProfilePhoto getProfilePhotoByUserUsername(@Param("username") String username);
}


