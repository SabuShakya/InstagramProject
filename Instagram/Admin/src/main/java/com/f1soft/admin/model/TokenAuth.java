package com.f1soft.admin.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "token_auth")
@Getter
@Setter
public class TokenAuth {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne
    private Admin admin;

    @Column(name = "token_no" ,unique = true)
    private String tokenNo;

    @Column(name = "status")
    private Character status;
}
