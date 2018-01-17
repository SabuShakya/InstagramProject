package com.users.repository;

import com.users.model.Comments;
import com.users.model.UserPhotos;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentsRepository extends JpaRepository<Comments,Long> {
    public List<Comments> getCommentsByUserUsername(String username);
}
