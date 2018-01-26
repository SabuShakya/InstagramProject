package com.users.dto;

import com.users.model.User;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class UserPhotodto {
    private long id;
    private List<String> imageList;
    private String image_path;
    private Date created_date;
    private String caption;
    private String username;
}
