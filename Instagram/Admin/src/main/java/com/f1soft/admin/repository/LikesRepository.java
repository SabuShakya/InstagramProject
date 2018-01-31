package com.f1soft.admin.repository;


import com.f1soft.admin.model.Likes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LikesRepository extends JpaRepository<Likes,Long>{
    @Query("select l from Likes l where l.user.id=:userId and l.userPhotos.id=:photoId")
    public Likes getByUserIdAndPhotoId(@Param("userId") Long userId,
                                       @Param("photoId") Long photoId);
    @Query("select l from Likes l where l.userPhotos.image_path=:imageName")
    public List<Likes> getByUserPhotos_Image_path(@Param("imageName") String imageName);

    public List<Likes> getByUserPhotos_Id(long id);
}
