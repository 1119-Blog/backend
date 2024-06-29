package com.petitjy.threadit.domain.article.controller;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.petitjy.threadit.domain.article.dto.ArticleRequest;
import com.petitjy.threadit.domain.article.entity.ArticleVisibility;
import com.petitjy.threadit.domain.article.service.impl.ArticleServiceImpl;
import com.petitjy.threadit.domain.member.dto.MemberDto;
import com.petitjy.threadit.global.error.ErrorCode;
import com.petitjy.threadit.global.exception.EntityNotFoundException;

@WebMvcTest(ArticleController.class)
public class ArticleControllerTest {
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private ArticleServiceImpl articleService;
	@Autowired
	private ObjectMapper objectMapper;

	private ArticleRequest articleRequest;
	private MemberDto memberDto;

	@BeforeEach
	void setUp() {
		articleRequest = ArticleRequest.builder()
			.id(1L)
			.title("제목을 입력하세요.")
			.content("내용을 입력하세요.")
			.hashtags("#java#spring")
			.visibility(ArticleVisibility.PUBLIC.value())
			.thumbnailPath("default.png")
			.blogId(1L)
			.categoryId(1L)
			.threadId(1L)
			.build();

		// TODO: 추후 ArgumentResolver 등록 시 수정될 사항
		memberDto = MemberDto.builder()
			.id(1L)
			.build();
	}


	@Test
	@DisplayName("게시글_등록_성공")
	void createArticleSuccess() throws Exception {
		doNothing().when(articleService).createArticle(any(ArticleRequest.class), any(Long.class));

		String content = objectMapper.writeValueAsString(articleRequest);
		mockMvc.perform(post("/v1/article")
				.contentType(MediaType.APPLICATION_JSON)
				.header(HttpHeaders.AUTHORIZATION, "Bearer access-token")
				.content(content))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.msg", is("게시글 저장 성공")))
			.andDo(print());

		verify(articleService).createArticle(any(ArticleRequest.class), any(Long.class));
	}

	@Test
	@DisplayName("게시글_등록_실패_Blog_not_found")
	void createArticleFailure() throws Exception {
		doThrow(new EntityNotFoundException(ErrorCode.BLOG_NOT_FOUND))
			.when(articleService).createArticle(any(ArticleRequest.class), any(Long.class));

		String content = objectMapper.writeValueAsString(articleRequest);
		mockMvc.perform(post("/v1/article")
				.contentType(MediaType.APPLICATION_JSON)
				.header(HttpHeaders.AUTHORIZATION, "Bearer access-token")
				.content(content))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.code", is(ErrorCode.BLOG_NOT_FOUND.getErrorCode())))
			.andExpect(jsonPath("$.msg", is(ErrorCode.BLOG_NOT_FOUND.getMsg())))
			.andDo(print());

		verify(articleService).createArticle(any(ArticleRequest.class), any(Long.class));
	}

	// TODO: AccessToken 관련 테스트

}
