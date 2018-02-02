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
    private Date created_date;
    private String caption;
    private String username;
}
