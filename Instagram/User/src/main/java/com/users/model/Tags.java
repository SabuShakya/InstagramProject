package com.users.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "tags_table")
@Getter
@Setter
public class Tags {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "tagged_by",nullable = false)
    private User taggedBy;

    @JoinColumn(name = "tagged_user",nullable = false)
    private User taggedUser;

    @Column(name = "is_tagged")
    private boolean isTagged;
}
