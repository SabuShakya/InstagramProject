package com.f1soft.admin.service;

import com.f1soft.admin.dto.Commentsdto;
import com.f1soft.admin.dto.UserPostDto;

import java.util.List;

public interface CommentsService {
    public List<Commentsdto> getComments(String imageName);
}
