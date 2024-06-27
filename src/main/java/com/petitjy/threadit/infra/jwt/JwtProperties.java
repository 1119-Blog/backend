package com.petitjy.threadit.infra.jwt;

import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.bind.ConstructorBinding;

@Getter
@ConfigurationProperties("jwt")
public class JwtProperties {
    private final String accessTokenExpireDuration;
    private final String refreshTokenExpireDuration;
    private final String accessTokenCookieName;
    private final String refreshTokenCookieName;
    private final String secret;

    @ConstructorBinding
    public JwtProperties(String accessTokenExpireDuration, String refreshTokenExpireDuration, String accessTokenCookieName, String refreshTokenCookieName, String secret) {
        this.accessTokenExpireDuration = accessTokenExpireDuration;
        this.refreshTokenExpireDuration = refreshTokenExpireDuration;
        this.accessTokenCookieName = accessTokenCookieName;
        this.refreshTokenCookieName = refreshTokenCookieName;
        this.secret = secret;
    }
}
