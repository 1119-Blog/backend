package com.petitjy.threadit.domain.article.entity;

import com.petitjy.threadit.domain.blog.entity.Blog;
import com.petitjy.threadit.domain.category.entity.Category;
import com.petitjy.threadit.domain.image.entity.Image;
import com.petitjy.threadit.domain.member.entity.Member;
import com.petitjy.threadit.domain.thread.entity.Thread;
import com.petitjy.threadit.global.entity.BaseTimeEntity;
import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "article")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Article extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100, nullable = false)
    private String title;

    @Column(length = 5000)
    private String content;

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

    @OneToOne
    @JoinColumn(name = "thumbnail_id", referencedColumnName = "id")
    private Image thumbnail;

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

    @Builder
    public Article(Long id, String title, String content, String hashtagStr, int hit, int threadOrder,
                   ArticleVisibility visibility, boolean isPublished, Image thumbnail, Member member, Blog blog,
                   Category category, Thread thread) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.hashtagStr = hashtagStr;
        this.hit = hit;
        this.threadOrder = threadOrder;
        this.visibility = visibility;
        this.isPublished = isPublished;
        this.thumbnail = thumbnail;
        this.member = member;
        this.blog = blog;
        this.category = category;
        this.thread = thread;
    }
}
