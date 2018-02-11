package com.f1soft.admin.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name="blockedUsers_table")
public class BlockUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name="userId")
    private User user;

    @ManyToOne
    @JoinColumn(name = "blocked_userId")
    private User blockedUser;

    @Column(name="blockStatus")
    private boolean blockStatus;
}
