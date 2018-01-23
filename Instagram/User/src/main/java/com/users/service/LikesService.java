package com.users.service;

import com.users.dto.Commentsdto;
import com.users.dto.Likesdto;
import com.users.model.Likes;

import java.util.List;

public interface LikesService {
    public int saveLike(Commentsdto commentsdto);
    public int getCountOfLikes(Likes likes);
    public List<Likes> getByPhotoId(long id);
    public int getLikesCountForImage(String imageName);
    public List<Likesdto> getLikesList(String imageName);
}
