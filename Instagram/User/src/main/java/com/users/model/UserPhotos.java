package com.users.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="photo_table" )
@Getter
@Setter
public class UserPhotos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long pic_id;

    @Column(name="image_path", nullable = false)
    private String image_path;

    @Column(name="created_date", nullable = false)
    private Date created_date;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User id;

    @Column(name ="caption")
    private String caption;

    @Column(name ="likes")
    private int likes;

    @Column(name ="comments")
    private String comments;

}