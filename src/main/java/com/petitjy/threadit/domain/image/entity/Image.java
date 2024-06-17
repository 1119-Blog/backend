package com.petitjy.threadit.domain.image.entity;

import com.petitjy.threadit.domain.article.entity.Article;
import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;

import java.sql.Timestamp;

@Entity
@Table(name = "image")
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 500, nullable = false)
    private String originalName;

    @Column(length = 200, nullable = false)
    private String fileName;

    @Column(length = 255, nullable = false)
    private String filePath;

    @Column(length = 20, nullable = false)
    private String ext;

    @ManyToOne
    @JoinColumn(name = "article_id", referencedColumnName="id", nullable = false)
    private Article article;

    @CreatedDate
    private Timestamp uploadedDate;
}

