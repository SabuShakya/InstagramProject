package com.users.dto;

import com.users.model.User;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class UserPhotodto {

    private String image_path;
    private Date created_date;
//    private User user_id;
    private String caption;
    private String likes;
    private String comments;
}
