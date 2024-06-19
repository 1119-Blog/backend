package com.petitjy.threadit.domain.image.entity;

import com.petitjy.threadit.domain.article.entity.Article;
import com.petitjy.threadit.domain.member.entity.Member;
import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;

import java.sql.Timestamp;

@Entity
@Table(name = "image")
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 200, nullable = false)
    private String fileName;

    @Column(length = 20, nullable = false)
    private String ext;

    @CreatedDate
    private Timestamp uploadedDate;

    @ManyToOne
    @JoinColumn(name = "member_id", referencedColumnName="id", nullable = false)
    private Member member;
}

