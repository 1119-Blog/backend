package com.petitjy.threadit.domain.article.entity;

import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;

import java.sql.Timestamp;

@Entity
@Table(name = "best_article")
public class BestArticle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreatedDate
    private Timestamp createdAt;

    private Timestamp deletedAt;

    @ManyToOne
    @JoinColumn(name = "article_id", referencedColumnName="id", nullable = false)
    private Article article;
}
