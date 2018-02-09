package com.f1soft.admin.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FollowCountDto {
    private long followers;
    private long following;
    private long totalPictures;
}
