package com.users.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="photo_table" )
@Getter
@Setter
public class UserPhotos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name="image_path", nullable = false)
    private String image_path;

//    @Column(name="profileImg")
//    private String profileImg;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="created_date")
    private Date created_date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id", nullable =false)
    private User user;

    @Column(name ="caption")
    private String caption;

    @OneToMany(mappedBy = "userPhotos")
    private List<Comments> comments;

    @OneToMany(mappedBy = "userPhotos")
    private List<Likes> likes;
}