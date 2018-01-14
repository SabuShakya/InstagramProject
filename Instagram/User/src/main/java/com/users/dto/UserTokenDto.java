package com.users.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserTokenDto {
    private Character status;
    private String username;
    private String tokenNo;
}
