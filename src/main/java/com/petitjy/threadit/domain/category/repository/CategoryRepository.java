package com.petitjy.threadit.domain.category.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.petitjy.threadit.domain.category.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
