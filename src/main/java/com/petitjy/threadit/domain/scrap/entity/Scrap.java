package com.petitjy.threadit.domain.scrap.entity;

import com.petitjy.threadit.domain.article.entity.Article;
import com.petitjy.threadit.domain.member.entity.Member;
import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
@Table(
        name = "scrap",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "scrap_unique_constraint",
                        columnNames = {"member_id", "article_id"}
                )
        })
public class Scrap {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Timestamp deletedAt;

    @ManyToOne
    @JoinColumn(name = "member_id", referencedColumnName = "id", nullable = false)
    private Member member;

    @ManyToOne
    @JoinColumn(name = "article_id", referencedColumnName="id", nullable = false)
    private Article article;
}

