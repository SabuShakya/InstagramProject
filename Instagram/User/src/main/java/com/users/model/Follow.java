package com.users.model;

import lombok.Getter;
import lombok.Setter;

import javax.jws.soap.SOAPBinding;
import javax.persistence.*;

@Entity
@Table(name = "follow")
@Getter
@Setter
public class Follow {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    @ManyToOne
    @JoinColumn(name = "following_userId")
    private User followedUser;
}
