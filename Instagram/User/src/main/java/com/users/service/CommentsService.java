package com.users.service;

import com.users.dto.Commentsdto;
import com.users.model.Comments;

import java.util.List;

public interface CommentsService{
    public void saveComments(Commentsdto commentsdto);
    public void deleteComment(Commentsdto commentsdto);
    public void updateComment(Commentsdto commentsdto);
    public List<Commentsdto> getAllComments(String image_path);
}
