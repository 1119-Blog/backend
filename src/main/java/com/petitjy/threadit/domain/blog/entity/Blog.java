package com.petitjy.threadit.domain.blog.entity;

import com.petitjy.threadit.domain.member.entity.Member;
import jakarta.persistence.*;
import lombok.Builder;

@Entity
@Table(name = "blog")
@Builder
public class Blog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 30, nullable = false)
    private String name;

    @Column(length = 20, nullable = false)
    private String subname;

    @OneToOne
    @JoinColumn(name = "member_id")
    private Member member;
}
