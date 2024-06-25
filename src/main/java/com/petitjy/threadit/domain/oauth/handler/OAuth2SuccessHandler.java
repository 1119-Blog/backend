package com.petitjy.threadit.domain.oauth.handler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import java.io.IOException;

@RequiredArgsConstructor
@Component
class OAuth2SuccessHandler implements AuthenticationSuccessHandler {
    @Value("${oauth.base-uri}")
    private String baseUri;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        response.setHeader("Content-Type", "application/json");

        ResponseCookie accessToken = ResponseCookie.from("access_token", "temp-token1")
                .path("/").httpOnly(true).build();
        ResponseCookie refreshToken = ResponseCookie.from("refresh_token", "temp-token2")
                .path("/").httpOnly(true).build();

        response.addHeader(HttpHeaders.SET_COOKIE, accessToken.toString());
        response.addHeader(HttpHeaders.SET_COOKIE, refreshToken.toString());

        response.setStatus(HttpServletResponse.SC_MOVED_PERMANENTLY);
        response.setHeader("Location", baseUri + "/");
    }
}
