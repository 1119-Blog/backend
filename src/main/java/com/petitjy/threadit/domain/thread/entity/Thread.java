package com.petitjy.threadit.domain.thread.entity;

import com.petitjy.threadit.domain.member.entity.Member;
import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;

import lombok.Builder;

@Entity
@Table(name = "thread")
@Builder
public class Thread {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @ColumnDefault("-1")
    private int lastIdx;

    @Column(length = 50, nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "member_id", referencedColumnName = "id")
    private Member member;
}
