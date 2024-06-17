package com.petitjy.threadit.domain.comment.entity;

import com.petitjy.threadit.domain.article.entity.Article;
import com.petitjy.threadit.domain.member.entity.Member;
import com.petitjy.threadit.global.entity.BaseTimeEntity;
import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;

import java.util.List;

@Entity
@Table(name = "comment")
public class Comment extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 1000, nullable = false)
    private String content;

    @Column(length = 30, nullable = false)
    @ColumnDefault("'PUBLIC'")
    @Enumerated(EnumType.STRING)
    private CommentVisibility visibility;

    @Column(length = 20)
    private String nickname;

    @Column(length = 255)
    private String password;

    @ManyToOne
    @JoinColumn(name = "article_id", referencedColumnName = "id", nullable = false)
    private Article article;

    @ManyToOne
    @JoinColumn(name = "member_id", referencedColumnName = "id")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "parent_id", referencedColumnName = "id")
    private Comment parent;

    @OneToMany(mappedBy = "parent")
    private List<Comment> children;
}

