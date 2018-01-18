package com.users.utils;

import com.users.dto.Commentsdto;
import com.users.model.Comments;

import java.util.ArrayList;
import java.util.List;

public class CommentUtils {
    public static List<Commentsdto> convertCommentsdtoToComments(List<Comments> commentsList) {
        List<Commentsdto> commentsdtoList = new ArrayList<Commentsdto>();
        for (Comments comments : commentsList) {
            Commentsdto commentsdto = new Commentsdto();
            commentsdto.setUsername(comments.getUser().getUsername());
            commentsdto.setImage_path(comments.getUserPhotos().getImage_path());
            commentsdto.setComments(comments.getComments());
            commentsdto.setComment_id(comments.getId());
            commentsdtoList.add(commentsdto);
        }
        return commentsdtoList;
    }
}
