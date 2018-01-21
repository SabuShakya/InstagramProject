package com.users.repository;

import com.users.model.Likes;
import org.hibernate.sql.Select;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LikesRepository extends JpaRepository<Likes,Long>{
    @Query("select l from Likes l where l.userPhotos.image_path=:imageName and l.user.username=:userName")
    public Likes getByUserPhotos_Image_pathAAndUser_Username(@Param("imageName") String imageName,
                                                             @Param("userName") String userName);
    @Query("select l from Likes l where l.userPhotos.image_path=:imageName")
    public List<Likes> getByUserPhotos_Image_path(@Param("imageName") String imageName);
}
