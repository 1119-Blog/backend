package com.petitjy.threadit.domain.oauth.dto;

import com.petitjy.threadit.domain.member.entity.Member;
import com.petitjy.threadit.domain.member.entity.MemberStatus;
import com.petitjy.threadit.domain.member.entity.Role;

import java.util.Map;

public record OAuth2MemberInfo(
        String nickname, String email, String profilePath) {

    public static OAuth2MemberInfo of(String registrationId, Map<String, Object> attributes) {
        return switch (registrationId) {
            case "google" -> ofGoogle(attributes);
            case "kakao" -> ofKakao(attributes);
            default -> throw new IllegalStateException("Unexpected value: " + registrationId);
        };
    }

    private static OAuth2MemberInfo ofGoogle(Map<String, Object> attributes) {
        return null;
    }

    private static OAuth2MemberInfo ofKakao(Map<String, Object> attributes) {
        Map<String, Object> accountInfo = (Map<String, Object>) attributes.get("kakao_account");
        Map<String, Object> profileInfo = (Map<String, Object>) accountInfo.get("profile");

        String email = (String)accountInfo.get("email");
        String nickname = (String) profileInfo.get("nickname");
        // TODO 기본 프로필 이미지 경로로 지정 or null?
        String profilePath = (String) profileInfo.get("profile_image_url");

        return new OAuth2MemberInfo(nickname, email, profilePath);
    }

    public Member toEntity() {
        return Member.builder()
                .nickname(nickname)
                .email(email)
                .profilePath(profilePath)
                .role(Role.MEMBER)
                .status(MemberStatus.ACTIVE)
                .build();
    }
}
