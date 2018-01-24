package com.f1soft.admin.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.validator.constraints.UniqueElements;

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

    @Column(name = "user_name",nullable = false, unique = true)
    private String userName;

    @Column(name = "password" ,nullable = false)
    private String password;

    @Column(name = "image")
    private String image;

    @OneToOne(mappedBy = "admin",fetch = FetchType.EAGER,cascade = CascadeType.REMOVE)
    private TokenAuth tokenAuth;
}
