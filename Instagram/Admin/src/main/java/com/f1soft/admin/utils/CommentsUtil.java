package com.f1soft.admin.utils;

import com.f1soft.admin.dto.Commentsdto;
import com.f1soft.admin.dto.UserPostDto;

import java.util.ArrayList;
import java.util.List;

public class CommentsUtil {
    public static Commentsdto convertToDto(Object[] o){

            Commentsdto commentsdto = new Commentsdto();
            commentsdto.setUsername(o[1].toString());
//            commentsdto.setImage_path(comments.getUserPhotos().getImage_path());
            commentsdto.setComments(o[0].toString());
//            commentsdto.setComment_id(.getId());
//            commentsdto.setShowCommentButtons(true);


        return commentsdto;
    }
}
