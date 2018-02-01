package com.f1soft.admin.repository;


import com.f1soft.admin.model.Comments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CommentsRepository extends JpaRepository<Comments,Long> {
    @Query("SELECT c from Comments c where c.userPhotos.image_path=:image_path")
    public List<Comments> getCommentsByUserPhotosImage_path(@Param("image_path") String image_path);
}
