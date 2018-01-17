package com.users.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserTokenDto {
    private String username;
    private String tokenNo;
    private Character status;
}
