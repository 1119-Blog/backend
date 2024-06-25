package com.petitjy.threadit.domain.auth.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@Slf4j
@RestController
@RequestMapping("/v1/auth")
public class AuthController {

    @Value("${oauth.redirect-uri}")
    private String oauthRedirectUri;

    @PostMapping("signin")
    public void signin() {
        // TODO 로컬 로그인 추가
    }

    @GetMapping("/{socialId}")
    public void socialAuth(@PathVariable(name = "socialId") String socialId, HttpServletResponse response) throws IOException {
        String redirectUri = oauthRedirectUri + socialId;
        response.sendRedirect(redirectUri);
    }
}
