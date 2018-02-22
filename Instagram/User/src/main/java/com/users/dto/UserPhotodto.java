package com.users.dto;

import com.users.model.Likes;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserPhotodto {
    private long id;
    private long pic_id;
    private List<ImageListdto> imageList;
    private String image_path;
    private String created_date;
    private String profile_pic;
    private String caption;
    private String username;
    private List<Likes> likes;
    private  int likesCount;

}
