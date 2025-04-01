package org.boot.capstone_1.entity;

import jakarta.persistence.*;

@Entity
@Table(name="users_toeic")
public class UsersToeic {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="users_toeic_id")
    private Integer usersToeicId;

    @ManyToOne
    @JoinColumn(name="users_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name="toeic_id", nullable = false)
    private Toeic toeic;
}
