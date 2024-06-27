package com.petitjy.threadit.infra.jwt;

import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.bind.ConstructorBinding;
import java.time.Duration;

@Getter
@ConfigurationProperties("jwt")
public class JwtProperties {
    private final Duration accessTokenExpireDuration;
    private final Duration refreshTokenExpireDuration;
    private final String accessTokenCookieName;
    private final String refreshTokenCookieName;
    private final String secret;

    @ConstructorBinding
    public JwtProperties(Duration accessTokenExpireDuration, Duration refreshTokenExpireDuration, String accessTokenCookieName, String refreshTokenCookieName, String secret) {
        this.accessTokenExpireDuration = accessTokenExpireDuration;
        this.refreshTokenExpireDuration = refreshTokenExpireDuration;
        this.accessTokenCookieName = accessTokenCookieName;
        this.refreshTokenCookieName = refreshTokenCookieName;
        this.secret = secret;
    }
}
