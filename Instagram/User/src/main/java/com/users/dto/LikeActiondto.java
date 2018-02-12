package com.users.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.GetMapping;

@Getter
@Setter
public class LikeActiondto {
    private int likeCount;
    private Boolean showRedButton;
}
