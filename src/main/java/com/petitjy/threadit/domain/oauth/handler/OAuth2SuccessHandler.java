package com.petitjy.threadit.domain.oauth.handler;

import com.petitjy.threadit.infra.jwt.JwtProvider;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import java.io.IOException;

@Component
class OAuth2SuccessHandler implements AuthenticationSuccessHandler {

    private final String BASE_URI;
    private final JwtProvider jwtProvider;

    public OAuth2SuccessHandler(@Value("${oauth.base-uri}") String baseUri, JwtProvider jwtProvider) {
        this.BASE_URI = baseUri;
        this.jwtProvider = jwtProvider;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        response.addHeader(HttpHeaders.CONTENT_TYPE, "application/json");

        String accessToken = jwtProvider.createToken(authentication, jwtProvider.getAccessTokenExpirationDate());
        String refreshToken = jwtProvider.createToken(authentication, jwtProvider.getRefreshTokenExpirationDate());

        ResponseCookie accessTokenCookie = ResponseCookie.from("access_token", accessToken)
                .path("/").httpOnly(true).build();
        ResponseCookie refreshTokenCookie = ResponseCookie.from("refresh_token", refreshToken)
                .path("/").httpOnly(true).build();
        // TODO Refresh token 저장

        response.addHeader(HttpHeaders.SET_COOKIE, accessTokenCookie.toString());
        response.addHeader(HttpHeaders.SET_COOKIE, refreshTokenCookie.toString());

        response.addHeader(HttpHeaders.LOCATION, BASE_URI);
        response.setStatus(HttpServletResponse.SC_MOVED_PERMANENTLY);
    }
}
