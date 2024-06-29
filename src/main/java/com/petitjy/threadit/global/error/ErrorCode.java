package com.petitjy.threadit.global.error;

import org.springframework.http.HttpStatus;

import com.petitjy.threadit.domain.hashtag.entity.Hashtag;

import lombok.Getter;

@Getter
public enum ErrorCode {

	// 1 : ENTITY_NOT_FOUND
	// 2 : INVALID_REQUEST
	// 3 : INTERVAL_SERVER_ERROR
	// ...

	// AU : Authentication & Authorization

	// A : Article
	ARTICLE_NOT_FOUND("A001", "Article not found."),


	// B : Blog
	BLOG_NOT_FOUND("B001", "Blog not found."),

	// C : Category
	CATEGORY_NOT_FOUND("C001", "Category not found."),

	// CM : Comment
	COMMENT_NOT_FOUND("CM001", "Comment not found."),

	// H : Hashtag
	HASHTAG_NOT_FOUND("H001", "Hashtag not found."),

	// I : Image
	IMAGE_NOT_FOUND("I001", "Image not found."),

	// L : Like
	LIKE_NOT_FOUND("L001", "Like not found."),

	// M : Member
	MEMBER_NOT_FOUND("M001", "Member not found."),

	// S : Scrap
	SCRAP_NOT_FOUND("S001", "Scrap not found."),

	// SB : Subscribe
	SUBSCRIBE_NOT_FOUND("SB001", "Subscribe not found."),

	// T : Thread
	THREAD_NOT_FOUND("T001", "Thread not found."),

	// ...
	;

	private String errorCode;
	private String msg;

	ErrorCode(String errorCode, String msg) {
		this.errorCode = errorCode;
		this.msg = msg;
	}
}
