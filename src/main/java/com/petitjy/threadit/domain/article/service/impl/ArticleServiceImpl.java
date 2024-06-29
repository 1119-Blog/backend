package com.petitjy.threadit.domain.article.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.petitjy.threadit.domain.article.dto.ArticleRequest;
import com.petitjy.threadit.domain.article.entity.Article;
import com.petitjy.threadit.domain.article.repository.ArticleRepository;
import com.petitjy.threadit.domain.article.service.ArticleService;
import com.petitjy.threadit.domain.blog.entity.Blog;
import com.petitjy.threadit.domain.blog.repository.BlogRepository;
import com.petitjy.threadit.domain.category.entity.Category;
import com.petitjy.threadit.domain.category.repository.CategoryRepository;
import com.petitjy.threadit.domain.image.entity.Image;
import com.petitjy.threadit.domain.image.repository.ImageRepository;
import com.petitjy.threadit.domain.member.entity.Member;
import com.petitjy.threadit.domain.member.repository.MemberRepository;
import com.petitjy.threadit.domain.thread.entity.Thread;
import com.petitjy.threadit.domain.thread.repository.ThreadRepository;
import com.petitjy.threadit.global.error.ErrorCode;
import com.petitjy.threadit.global.exception.EntityNotFoundException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class ArticleServiceImpl implements ArticleService {
	private final ArticleRepository articleRepository;
	private final ImageRepository imageRepository;
	private final MemberRepository memberRepository;
	private final BlogRepository blogRepository;
	private final CategoryRepository categoryRepository;
	private final ThreadRepository threadRepository;

	@Override
	public void createArticle(ArticleRequest articleRequest, Long memberId) {
		log.info("ArticleServiceImpl.createArticle() is called.");

		// TODO: ThumbnailPath 전처리
		// TODO: save() 실패 시 예외처리

		Image thumbnail = articleRequest.getThumbnailPath() == null ?
			null : imageRepository.findByFileName(articleRequest.getThumbnailPath())
			.orElseThrow(() -> new EntityNotFoundException(ErrorCode.IMAGE_NOT_FOUND));
		Member member = memberRepository.findById(memberId)
			.orElseThrow(() -> new EntityNotFoundException(ErrorCode.MEMBER_NOT_FOUND));
		Blog blog = blogRepository.findById(articleRequest.getBlogId())
			.orElseThrow(() -> new EntityNotFoundException(ErrorCode.BLOG_NOT_FOUND));
		Category category = articleRequest.getCategoryId() == null ?
			null : categoryRepository.findById(articleRequest.getCategoryId())
			.orElseThrow(() -> new EntityNotFoundException(ErrorCode.CATEGORY_NOT_FOUND));
		Thread thread = articleRequest.getThreadId() == null ?
			null : threadRepository.findById(articleRequest.getThreadId())
			.orElseThrow(() -> new EntityNotFoundException(ErrorCode.THREAD_NOT_FOUND));

		Article article = ArticleRequest.toEntity(articleRequest, thumbnail, member, blog, category, thread);
		articleRepository.save(article);
	}
}
