package com.petitjy.threadit.domain.article.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.petitjy.threadit.domain.article.dto.ArticleRequest;
import com.petitjy.threadit.domain.article.service.ArticleService;
import com.petitjy.threadit.domain.member.dto.MemberDto;
import com.petitjy.threadit.global.dto.ApiResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/v1")
@RequiredArgsConstructor
@Tag(name = "Article Controller", description = "게시글에 대한 등록, 조회, 수정, 삭제를 담당하는 클래스")
public class ArticleController {

	private final ArticleService articleService;

//	@Operation(security = @SecurityRequirement(name = "JWT"))
//	@GetMapping("test")
//	public String swaggerTest(HttpServletRequest request) {
//		String token = request.getHeader("Authorization");
//		return "스웨거 응답 확인, token=" + token;
//	}

	@Operation(summary = "게시글 등록/수정", security = @SecurityRequirement(name = "JWT"))
	@io.swagger.v3.oas.annotations.parameters.RequestBody(description = "게시글 정보", required = true,
		content = @Content(schema = @Schema(implementation = ArticleRequest.class)))
	@PostMapping("/article")
	public ApiResponse<?> createArticle(@RequestBody ArticleRequest articleRequest, MemberDto memberDto) {
		log.info("ArticleController.createArticle() is called.");
		memberDto.setId(1L); // TODO: 삭제 예정, accessToken 값 대신 사용

		articleService.createArticle(articleRequest, memberDto.getId());
		// TODO: 이미지 리스트 받아 저장하는 로직 필요

		return ApiResponse.onSuccess(HttpStatus.OK, "게시글 저장 성공");
	}
}
