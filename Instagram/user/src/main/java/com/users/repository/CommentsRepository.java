package com.users.repository;

import com.users.model.Comments;
import com.users.model.UserPhotos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CommentsRepository extends JpaRepository<Comments,Long> {
    @Query("SELECT c from Comments c where c.userPhotos.image_path=:image_path")
    public List<Comments> getCommentsByUserPhotosImage_path(@Param("image_path")String image_path);
}
