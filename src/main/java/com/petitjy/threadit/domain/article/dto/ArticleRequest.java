package com.petitjy.threadit.domain.article.dto;

import com.petitjy.threadit.domain.article.entity.Article;
import com.petitjy.threadit.domain.article.entity.ArticleVisibility;
import com.petitjy.threadit.domain.blog.entity.Blog;
import com.petitjy.threadit.domain.category.entity.Category;
import com.petitjy.threadit.domain.image.entity.Image;
import com.petitjy.threadit.domain.member.entity.Member;
import com.petitjy.threadit.domain.thread.entity.Thread;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Schema(title = "ArticleRequest (게시글 요청 객체)", description = "게시글 등록/수정을 위한 정보를 담은 객체")
@Getter
@Setter
@Builder
@ToString
public class ArticleRequest {
	@Schema(description = "게시글 번호", example = "1")
	private Long id;
	@Schema(description = "게시글 제목", example = "제목을 입력하세요.")
	private String title;
	@Schema(description = "게시글 내용", example = "내용을 입력하세요.")
	private String content;
	@Schema(description = "해시태그 목록", example = "#java#spring")
	private String hashtags;
	@Schema(description = "발행여부", example = "PUBLIC")
	private String visibility;
	@Schema(description = "섬네일", example = "default.png")
	private String thumbnailPath;
	@Schema(description = "블로그 번호", example = "1")
	private Long blogId;
	@Schema(description = "카테고리 번호", example = "1")
	private Long categoryId;
	@Schema(description = "스레드 번호", example = "1")
	private Long threadId;


	public static Article toEntity(ArticleRequest articleRequest, Image image, Member member, Blog blog, Category category, Thread thread) {
		return Article.builder()
			.id(articleRequest.id)
			.title(articleRequest.title)
			.content(articleRequest.content)
			.hashtagStr(articleRequest.hashtags)
			.visibility(ArticleVisibility.valueOf(articleRequest.visibility))
			.thumbnail(image)
			.member(member)
			.blog(blog)
			.category(category)
			.thread(thread)
			.build();
	}
}
