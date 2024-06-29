package com.petitjy.threadit.domain.thread.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.petitjy.threadit.domain.thread.entity.Thread;

public interface ThreadRepository extends JpaRepository<Thread, Long> {
}
