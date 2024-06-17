package com.petitjy.threadit.domain.article.entity;

import com.petitjy.threadit.domain.blog.entity.Blog;
import com.petitjy.threadit.domain.category.entity.Category;
import com.petitjy.threadit.domain.member.entity.Member;
import com.petitjy.threadit.domain.thread.entity.Thread;
import com.petitjy.threadit.global.entity.BaseTimeEntity;
import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;

@Entity
@Table(name = "article")
public class Article extends BaseTimeEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100, nullable = false)
    private String title;

    @Column(length = 5000)
    private String content;

    @Column(length = 255, nullable = false)
    private String thumbnailPath;

    @Column(length = 255)
    private String hashtagStr;

    @Column(nullable = false)
    @ColumnDefault("0")
    private int hit;

    @Column(nullable = false)
    @ColumnDefault("0")
    private int threadOrder;

    @Column(length = 30, nullable = false)
    @Enumerated(EnumType.STRING)
    @ColumnDefault("'PUBLIC'")
    private ArticleVisibility visibility;

    @Column(nullable = false)
    private boolean isPublished;

    @ManyToOne
    @JoinColumn(name = "member_id", referencedColumnName = "id", nullable = false)
    private Member member;

    @ManyToOne
    @JoinColumn(name = "blog_id", referencedColumnName = "id", nullable = false)
    private Blog blog;

    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "thread_id", referencedColumnName = "id")
    private Thread thread;
}
