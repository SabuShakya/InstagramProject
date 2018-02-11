package com.users.utils;

import com.users.dto.Commentsdto;
import com.users.dto.Likesdto;
import com.users.model.Likes;
import com.users.model.User;
import com.users.model.UserPhotos;

import java.util.ArrayList;
import java.util.List;

public class LikesUtil {
    public static Likes generateLikes(User user, UserPhotos userPhotos){
        Likes likes = new Likes();
        likes.setUser(user);
        likes.setUserPhotos(userPhotos);
        likes.setLiked(true);
        return likes;
    }

    public static List<Likesdto> convertLikesToLikesDto(List<Likes> likesList){
        List<Likesdto> likesdtoList = new ArrayList<Likesdto>();
        for (Likes likes:likesList){
            Likesdto likesdto =  new Likesdto();
            likesdto.setUserName(likes.getUser().getUsername());
            likesdto.setActivationStatus(likes.getUser().getUserActivation().getActivationStatus());
            likesdto.setImageName(likes.getUserPhotos().getImage_path());
            likesdtoList.add(likesdto);
        }
        return likesdtoList;
    }
}
