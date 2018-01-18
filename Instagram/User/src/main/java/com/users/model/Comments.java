package com.users.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@Table(name ="comment_table")
public class Comments implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name ="comments")
    private String comments;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="user_id", nullable =false)
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="pic_id", nullable = false)
    private UserPhotos userPhotos;
}
