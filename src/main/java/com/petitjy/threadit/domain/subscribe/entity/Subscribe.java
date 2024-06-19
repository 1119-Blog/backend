package com.petitjy.threadit.domain.subscribe.entity;

import com.petitjy.threadit.domain.blog.entity.Blog;
import com.petitjy.threadit.domain.member.entity.Member;
import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
@Table(
        name = "subscribe",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "subscribe_unique_constraint",
                        columnNames = {"member_id", "blog_id"}
                )
        })
public class Subscribe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Timestamp deletedAt;

    @ManyToOne
    @JoinColumn(name = "member_id", referencedColumnName = "id", nullable = false)
    private Member member;

    @ManyToOne
    @JoinColumn(name = "blog_id", referencedColumnName = "id", nullable = false)
    private Blog blog;
}
