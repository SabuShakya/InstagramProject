package com.users.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class UserPostDto {
    private String username;
    private String image_path;
    private List<String> comments;
    private String created_date;
    private String caption;
}
