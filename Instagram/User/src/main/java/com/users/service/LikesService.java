package com.users.service;

import com.users.dto.Likesdto;

import java.util.List;

public interface LikesService {
    public void saveLikes(Likesdto likesdto);
    public List<Likesdto> getAllLikes(String image_path);
}
