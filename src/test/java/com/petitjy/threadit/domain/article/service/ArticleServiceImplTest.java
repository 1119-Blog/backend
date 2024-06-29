package com.petitjy.threadit.domain.article.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.petitjy.threadit.domain.article.dto.ArticleRequest;
import com.petitjy.threadit.domain.article.entity.Article;
import com.petitjy.threadit.domain.article.entity.ArticleVisibility;
import com.petitjy.threadit.domain.article.repository.ArticleRepository;
import com.petitjy.threadit.domain.article.service.impl.ArticleServiceImpl;
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

@ExtendWith(MockitoExtension.class)
public class ArticleServiceImplTest {

	@Mock
	private ArticleRepository articleRepository;
	@Mock
	private ImageRepository imageRepository;
	@Mock
	private MemberRepository memberRepository;
	@Mock
	private BlogRepository blogRepository;
	@Mock
	private CategoryRepository categoryRepository;
	@Mock
	private ThreadRepository threadRepository;
	@InjectMocks
	private ArticleServiceImpl articleService;

	private ArticleRequest articleRequest;
	private Long memberId;

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
		memberId = 1L;
	}

	@Test
	@DisplayName("게시글_등록_성공")
	void createArticleSuccess() {
		Image thumbnail = Image.builder().build();
		Member member = Member.builder().build();
		Blog blog = Blog.builder().build();
		Category category = Category.builder().build();
		Thread thread = Thread.builder().build();

		given(imageRepository.findByFileName(articleRequest.getThumbnailPath())).willReturn(Optional.of(thumbnail));
		given(memberRepository.findById(memberId)).willReturn(Optional.of(member));
		given(blogRepository.findById(articleRequest.getBlogId())).willReturn(Optional.of(blog));
		given(categoryRepository.findById(articleRequest.getCategoryId())).willReturn(Optional.of(category));
		given(threadRepository.findById(articleRequest.getThreadId())).willReturn(Optional.of(thread));
		given(articleRepository.save(any(Article.class))).willReturn(Article.builder().build());

		articleService.createArticle(articleRequest, memberId);

		verify(imageRepository).findByFileName(articleRequest.getThumbnailPath());
		verify(memberRepository).findById(memberId);
		verify(blogRepository).findById(articleRequest.getBlogId());
		verify(categoryRepository).findById(articleRequest.getCategoryId());
		verify(threadRepository).findById(articleRequest.getThreadId());
		verify(articleRepository).save(any(Article.class));
	}

	@Test
	@DisplayName("게시글_등록_실패_Image_not_found")
	void createArticleImageNotFound() {
		given(imageRepository.findByFileName(articleRequest.getThumbnailPath())).willReturn(Optional.empty());

		EntityNotFoundException exception = assertThrows(EntityNotFoundException.class, () -> {
			articleService.createArticle(articleRequest, memberId);
		});
		assertEquals(exception.getErrorCode().getErrorCode(), ErrorCode.IMAGE_NOT_FOUND.getErrorCode());
		assertEquals(exception.getMessage(), ErrorCode.IMAGE_NOT_FOUND.getMsg());

		verify(imageRepository).findByFileName(articleRequest.getThumbnailPath());
	}

	@Test
	@DisplayName("게시글_등록_실패_Member_not_found")
	void createArticleMemberNotFound() {
		given(imageRepository.findByFileName(articleRequest.getThumbnailPath())).willReturn(
			Optional.of(Image.builder().build()));
		given(memberRepository.findById(memberId)).willReturn(Optional.empty());

		EntityNotFoundException exception = assertThrows(EntityNotFoundException.class, () -> {
			articleService.createArticle(articleRequest, memberId);
		});
		assertEquals(exception.getErrorCode().getErrorCode(), ErrorCode.MEMBER_NOT_FOUND.getErrorCode());
		assertEquals(exception.getMessage(), ErrorCode.MEMBER_NOT_FOUND.getMsg());

		verify(imageRepository).findByFileName(articleRequest.getThumbnailPath());
		verify(memberRepository).findById(memberId);
	}

	@Test
	@DisplayName("게시글_등록_실패_Blog_not_found")
	void createArticleBlogNotFound() {
		given(imageRepository.findByFileName(articleRequest.getThumbnailPath())).willReturn(
			Optional.of(Image.builder().build()));
		given(memberRepository.findById(memberId)).willReturn(Optional.of(Member.builder().build()));
		given(blogRepository.findById(articleRequest.getBlogId())).willReturn(Optional.empty());

		EntityNotFoundException exception = assertThrows(EntityNotFoundException.class, () -> {
			articleService.createArticle(articleRequest, memberId);
		});
		assertEquals(exception.getErrorCode().getErrorCode(), ErrorCode.BLOG_NOT_FOUND.getErrorCode());
		assertEquals(exception.getMessage(), ErrorCode.BLOG_NOT_FOUND.getMsg());

		verify(imageRepository).findByFileName(articleRequest.getThumbnailPath());
		verify(memberRepository).findById(memberId);
		verify(blogRepository).findById(articleRequest.getBlogId());
	}

	@Test
	@DisplayName("게시글_등록_실패_Category_not_found")
	void createArticleCategoryNotFound() {
		given(imageRepository.findByFileName(articleRequest.getThumbnailPath())).willReturn(
			Optional.of(Image.builder().build()));
		given(memberRepository.findById(memberId)).willReturn(Optional.of(Member.builder().build()));
		given(blogRepository.findById(articleRequest.getBlogId())).willReturn(Optional.of(Blog.builder().build()));
		given(categoryRepository.findById(articleRequest.getCategoryId())).willReturn(Optional.empty());

		EntityNotFoundException exception = assertThrows(EntityNotFoundException.class, () -> {
			articleService.createArticle(articleRequest, memberId);
		});
		assertEquals(exception.getErrorCode().getErrorCode(), ErrorCode.CATEGORY_NOT_FOUND.getErrorCode());
		assertEquals(exception.getMessage(), ErrorCode.CATEGORY_NOT_FOUND.getMsg());

		verify(imageRepository).findByFileName(articleRequest.getThumbnailPath());
		verify(memberRepository).findById(memberId);
		verify(blogRepository).findById(articleRequest.getBlogId());
		verify(categoryRepository).findById(articleRequest.getCategoryId());
	}

	@Test
	@DisplayName("게시글_등록_실패_Thread_not_found")
	void createArticleThreadNotFound() {
		given(imageRepository.findByFileName(articleRequest.getThumbnailPath())).willReturn(
			Optional.of(Image.builder().build()));
		given(memberRepository.findById(memberId)).willReturn(Optional.of(Member.builder().build()));
		given(blogRepository.findById(articleRequest.getBlogId())).willReturn(Optional.of(Blog.builder().build()));
		given(categoryRepository.findById(articleRequest.getCategoryId())).willReturn(
			Optional.of(Category.builder().build()));
		given(threadRepository.findById(articleRequest.getThreadId())).willReturn(Optional.empty());

		EntityNotFoundException exception = assertThrows(EntityNotFoundException.class, () -> {
			articleService.createArticle(articleRequest, memberId);
		});
		assertEquals(exception.getErrorCode().getErrorCode(), ErrorCode.THREAD_NOT_FOUND.getErrorCode());
		assertEquals(exception.getMessage(), ErrorCode.THREAD_NOT_FOUND.getMsg());

		verify(imageRepository).findByFileName(articleRequest.getThumbnailPath());
		verify(memberRepository).findById(memberId);
		verify(blogRepository).findById(articleRequest.getBlogId());
		verify(categoryRepository).findById(articleRequest.getCategoryId());
		verify(threadRepository).findById(articleRequest.getThreadId());
	}
}
