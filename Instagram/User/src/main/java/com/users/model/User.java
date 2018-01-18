package com.users.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name= "user_table")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name="fullName", nullable = false)
    private String fullName;

    @Column(name="username", nullable = false, unique = true)
    private String username;

    @Column(name="email", nullable = false)
    private String email;

    @Column(name="password", nullable = false)
    private String password;

    @OneToMany(mappedBy = "user")
    private List<UserPhotos> userPhotos;

    @OneToOne(mappedBy = "user")
    private UserToken userTokenAuth;

    @OneToMany(mappedBy = "user")
    private List<Follow> followingUser;

    @OneToMany(mappedBy = "followedUser")
    private List<Follow> followedUser;

    @OneToMany(mappedBy = "user")
    private List<Comments> comments;
}

