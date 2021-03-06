package com.f1soft.admin.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@Table(name ="likes_table")
public class Likes implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id", nullable =false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="pic_id", nullable = false)
    private UserPhotos userPhotos;

    @Column(name = "is_liked")
    private boolean isLiked;
}
