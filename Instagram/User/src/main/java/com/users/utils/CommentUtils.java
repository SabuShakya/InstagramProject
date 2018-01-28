package com.users.utils;

import com.users.dto.Commentsdto;
import com.users.model.Comments;

import javax.xml.stream.events.Comment;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CommentUtils {
    public static List<Commentsdto> convertCommentsToCommentsdto(List<Comments> commentsList) {
        List<Commentsdto> commentsdtoList = new ArrayList<Commentsdto>();
        for (Comments comments : commentsList) {
            Commentsdto commentsdto = new Commentsdto();
            commentsdto.setUsername(comments.getUser().getUsername());
            commentsdto.setImage_path(comments.getUserPhotos().getImage_path());
            commentsdto.setComments(comments.getComments());
            commentsdto.setComment_id(comments.getId());
            commentsdto.setShowCommentButtons(true);
            commentsdtoList.add(commentsdto);
        }
        return commentsdtoList;
    }
}
