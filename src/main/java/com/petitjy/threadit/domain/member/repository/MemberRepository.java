package com.petitjy.threadit.domain.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.petitjy.threadit.domain.member.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
