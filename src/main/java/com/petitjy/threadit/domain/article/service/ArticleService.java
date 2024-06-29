package com.petitjy.threadit.domain.article.service;

import com.petitjy.threadit.domain.article.dto.ArticleRequest;

public interface ArticleService {
	void createArticle(ArticleRequest articleRequest, Long memberId);
}
