package com.users.repository;

import com.users.model.Likes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LikesRepository extends JpaRepository<Likes,Long>{
    @Query("SELECT l from Likes l where l.userPhotos.image_path=:image_path")
    public List<Likes> getLikesByUserPhotosImage_path(@Param("image_path") String image_path);
}
