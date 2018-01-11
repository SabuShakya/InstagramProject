package com.f1soft.admin.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name = "admin_info")
@Getter
@Setter
public class Admin implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name",nullable = false)
    private String name;

    @Column(name = "email",nullable = false)
    private String email;

    @NotNull
    @Column(name = "user_id",nullable = false)
    private String userId;

    @NotNull
    @Column(name = "password" ,nullable = false)
    private String password;

    @Column(name = "image")
    private String image;

    @Column(name = "token_no" ,unique = true)
    private String tokenNo;
}
