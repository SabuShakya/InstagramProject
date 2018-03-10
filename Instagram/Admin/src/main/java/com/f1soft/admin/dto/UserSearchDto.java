package com.f1soft.admin.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserSearchDto {
    private Long userId;
    private String username;
    private String fullname;
    private String imagename;
    private boolean showResultButtons;
    private String activationStatus;
    private boolean blockStatus;
    private boolean hideButtons;
}
