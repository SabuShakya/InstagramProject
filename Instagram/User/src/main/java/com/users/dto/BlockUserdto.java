package com.users.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BlockUserdto {
    private long id;
    private String userName;
    private String blockedUsername;
    private String following_userName;
}

