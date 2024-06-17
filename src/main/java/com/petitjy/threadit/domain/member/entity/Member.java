package com.petitjy.threadit.domain.member.entity;

import com.petitjy.threadit.global.entity.BaseTimeEntity;
import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;

@Entity
@Table(name = "member")
public class Member extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 320, nullable = false)
    private String email;

    @Column(length = 255)
    private String password;

    @Column(length = 10, nullable = false)
    @Enumerated(EnumType.STRING)
    @ColumnDefault("'MEMBER'")
    private Role role;

    @Column(length = 20, nullable = false)
    private String nickname;

    @Column(length = 255)
    private String profilePath;

    @Column(length = 10, nullable = false)
    @Enumerated(EnumType.STRING)
    @ColumnDefault("'ACTIVE'")
    private MemberStatus status;
}