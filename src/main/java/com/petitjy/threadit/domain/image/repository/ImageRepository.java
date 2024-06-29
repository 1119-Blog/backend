package com.petitjy.threadit.domain.image.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.petitjy.threadit.domain.image.entity.Image;

public interface ImageRepository extends JpaRepository<Image, Long> {
	Optional<Image> findByFileName(String thumbnailPath);
}
