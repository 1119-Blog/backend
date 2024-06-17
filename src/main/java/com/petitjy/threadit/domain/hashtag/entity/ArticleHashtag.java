package com.petitjy.threadit.domain.hashtag.entity;

import com.petitjy.threadit.domain.article.entity.Article;
import jakarta.persistence.*;

@Entity
@Table(
        name = "article_hashtag",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "article_hashtag_unique_constraint",
                        columnNames = {"hashtag_id", "article_id"}
                )
        })
public class ArticleHashtag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "hashtag_id", referencedColumnName = "id", nullable = false)
    private Hashtag hashtagId;

    @ManyToOne
    @JoinColumn(name = "article_id", referencedColumnName="id", nullable = false)
    private Article article;
}
