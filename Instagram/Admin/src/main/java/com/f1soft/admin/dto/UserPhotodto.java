package com.f1soft.admin.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class UserPhotodto {
    private long id;
    private long photo_id;
    private List<String> imageList;
    private String image_path;
    private String created_date;
    private String caption;
    private String username;
    private String profile_pic;
}
