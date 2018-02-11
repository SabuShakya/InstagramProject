package com.f1soft.admin.service;

import com.f1soft.admin.dto.Commentsdto;
import com.f1soft.admin.dto.Likesdto;
import com.f1soft.admin.model.Likes;


import java.util.List;

public interface LikesService {
    public int saveLike(Commentsdto commentsdto);
    public int getCountOfLikes(Likes likes);
    public List<Likes> getByPhotoId(long id);
    public int getLikesCountForImage(String imageName);
    public List<Likesdto> getLikesList(String imageName);
}
