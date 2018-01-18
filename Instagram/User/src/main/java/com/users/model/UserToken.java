package com.users.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "user_token_auth")
@Getter
@Setter
public class UserToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "status")
    private Character status;

    @Column(name = "token_no", unique = true)
    private String tokenNo;

    @OneToOne
    private User user;
}
