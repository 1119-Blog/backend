package com.petitjy.threadit.domain.article.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.petitjy.threadit.domain.article.entity.Article;

public interface ArticleRepository extends JpaRepository<Article, Long> {
}
