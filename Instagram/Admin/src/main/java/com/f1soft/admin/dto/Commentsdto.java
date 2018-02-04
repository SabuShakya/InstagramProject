package com.f1soft.admin.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class Commentsdto implements Serializable{
    private String comments;
    private Long comment_id;
    private String username;
    private String image_path;
    private boolean showCommentButtons;
}
