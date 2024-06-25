package com.petitjy.threadit.domain.member.entity;

import com.petitjy.threadit.global.entity.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

@Getter
@Entity
@Table(name = "member")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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

    @Builder
    private Member(Long id, String email, String password, Role role, String nickname, String profilePath, MemberStatus status) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.role = role;
        this.nickname = nickname;
        this.profilePath = profilePath;
        this.status = status;
    }
}