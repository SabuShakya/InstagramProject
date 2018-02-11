package com.users.dto;

import com.users.model.Comments;
import com.users.model.Likes;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Getter
@Setter
public class UserPostDto {
    private String username;
    private String image_path;
    private String profilePic;
    private List<String> comments;
    private String created_date;
    private String caption;
    private int countOfLikes;
    private int totalItems;
    private List<Likes> likes;

    private String following_userId;
}
