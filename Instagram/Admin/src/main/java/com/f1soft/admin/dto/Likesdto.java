package com.f1soft.admin.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Likesdto {
    private String userName;
    private String imageName;
    private int likeCount;
    private String activationStatus;
}
