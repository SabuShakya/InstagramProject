package com.users.dto;

import com.users.model.User;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class UserPhotodto {
    private long pic_id;
    private String image_path;
    private Date created_date;
    private User id;
    private String caption;
    private String likes;
    private String comments;
}
