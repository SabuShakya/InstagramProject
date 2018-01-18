package com.users.utils;

import com.users.dto.Likesdto;
import com.users.model.Likes;

import java.util.ArrayList;
import java.util.List;

public class LikesUtils {
    public static List<Likesdto> convertLikestoLikesdto(List<Likes> likesList) {
        List<Likesdto> likesdtoList = new ArrayList<Likesdto>();
        for (Likes likes : likesList) {
            Likesdto likesdto = new Likesdto();
            likesdto.setUsername(likes.getUser().getUsername());
            likesdto.setImage_path(likes.getUserPhotos().getImage_path());
            likesdto.setLikes(likes.getLikes());
            likesdtoList.add(likesdto);
            }
            return likesdtoList;
        }
    }
