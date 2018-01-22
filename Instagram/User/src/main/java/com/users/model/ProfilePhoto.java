package com.users.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="profile_pic_table")
@Getter
@Setter
public class ProfilePhoto implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name="profile_pic")
    private String profile_pic;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id", nullable =false)
    private User user;
}
