package com.petitjy.threadit.domain.oauth.service;

import com.petitjy.threadit.domain.member.entity.Member;
import com.petitjy.threadit.domain.member.repository.MemberRepository;
import com.petitjy.threadit.domain.oauth.dto.MemberPrincipalDetails;
import com.petitjy.threadit.domain.oauth.dto.OAuth2MemberInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
class CustomOauth2MemberService extends DefaultOAuth2UserService {
    private final MemberRepository memberRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        Map<String, Object> attributes = super.loadUser(userRequest).getAttributes();
        String userNameAttributeName = userRequest.getClientRegistration().getProviderDetails().getUserInfoEndpoint().getUserNameAttributeName();

        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        OAuth2MemberInfo oAuth2MemberInfo = OAuth2MemberInfo.of(registrationId, attributes);

        Member member = loginOrSignup(oAuth2MemberInfo);

        return new MemberPrincipalDetails(member, attributes, userNameAttributeName);
    }

    protected Member loginOrSignup(OAuth2MemberInfo oAuth2Member) {
        Member member =
                memberRepository.findByEmail(oAuth2Member.email())
                .orElseGet(() -> memberRepository.save(oAuth2Member.toEntity()));
        log.debug("logined member: {} {}", member.getId(), member.getNickname());
        return member;
    }
}
