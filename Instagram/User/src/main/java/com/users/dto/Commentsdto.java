package com.users.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class Commentsdto implements Serializable{
    private String comments;
    private String username;
    private String image_path;
}
