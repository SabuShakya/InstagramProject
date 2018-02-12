package com.users.service;

import com.users.dto.Commentsdto;
import com.users.dto.LikeActiondto;
import com.users.dto.Likesdto;
import com.users.model.Likes;
import com.users.model.User;

import java.util.List;

public interface LikesService {
    public LikeActiondto saveLike(Commentsdto commentsdto);
    public int getCountOfLikes(Likes likes);
    public List<Likes> getByPhotoId(long id);
    public LikeActiondto getLikesCountForImage(String imageName, User user);
    public List<Likesdto> getLikesList(String imageName);
}
