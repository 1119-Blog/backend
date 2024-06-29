package com.petitjy.threadit.domain.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.petitjy.threadit.domain.blog.entity.Blog;

public interface BlogRepository extends JpaRepository<Blog, Long> {
}
