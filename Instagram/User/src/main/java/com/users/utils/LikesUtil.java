package com.users.utils;

import com.users.dto.Commentsdto;
import com.users.dto.Likesdto;
import com.users.model.Likes;
import com.users.model.User;
import com.users.model.UserPhotos;

public class LikesUtil {
    public static Likes generateLikes(User user, UserPhotos userPhotos){
        Likes likes = new Likes();
        likes.setUser(user);
        likes.setUserPhotos(userPhotos);
        likes.setLiked(true);
        return likes;
    }
}
