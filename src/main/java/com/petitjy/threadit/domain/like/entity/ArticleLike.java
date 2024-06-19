package com.petitjy.threadit.domain.like.entity;

import com.petitjy.threadit.domain.article.entity.Article;
import com.petitjy.threadit.domain.member.entity.Member;
import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;

import java.sql.Timestamp;

@Entity
@Table(name = "articlelike")
public class ArticleLike {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Timestamp deletedAt;

    @ManyToOne
    @JoinColumn(name = "article_id", referencedColumnName="id", nullable = false)
    private Article article;

    @ManyToOne
    @JoinColumn(name = "member_id", referencedColumnName = "id", nullable = false)
    private Member member;
}
