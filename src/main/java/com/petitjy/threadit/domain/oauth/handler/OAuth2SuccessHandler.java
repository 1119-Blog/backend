package com.petitjy.threadit.domain.oauth.handler;

import com.petitjy.threadit.domain.member.entity.Member;
import com.petitjy.threadit.domain.oauth.dto.MemberPrincipalDetails;
import com.petitjy.threadit.infra.jwt.JwtProperties;
import com.petitjy.threadit.infra.jwt.JwtProvider;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import java.io.IOException;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
class OAuth2SuccessHandler implements AuthenticationSuccessHandler {
    @Value("${oauth.base-uri}")
    private String baseUri;

    private final JwtProvider jwtProvider;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        response.setHeader("Content-Type", "application/json");

        String accessToken = jwtProvider.createToken(authentication, jwtProvider.getAccessTokenExpirationDate());
        String refreshToken = jwtProvider.createToken(authentication, jwtProvider.getRefreshTokenExpirationDate());

        ResponseCookie accessTokenCookie = ResponseCookie.from("access_token", accessToken)
                .path("/").httpOnly(true).build();
        ResponseCookie refreshTokenCookie = ResponseCookie.from("refresh_token", refreshToken)
                .path("/").httpOnly(true).build();

        response.addHeader(HttpHeaders.SET_COOKIE, accessTokenCookie.toString());
        response.addHeader(HttpHeaders.SET_COOKIE, refreshTokenCookie.toString());

        response.setStatus(HttpServletResponse.SC_MOVED_PERMANENTLY);
        response.setHeader("Location", baseUri + "/");
    }
}
