package com.petitjy.threadit.global.config;

import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.bind.ConstructorBinding;

@Getter
@ConfigurationProperties("spring.data.redis")
public class RedisProperties {
    private final String host;
    private final int port;
    private final String password;

    @ConstructorBinding
    public RedisProperties(String host, int port, String password) {
        this.host = host;
        this.port = port;
        this.password = password;
    }
}
