package com.users.dto;

import com.users.model.ProfilePhoto;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class FollowDto {
    private String userName;
    private String following_userName;
    private String image;
}
