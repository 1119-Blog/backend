package com.petitjy.threadit.infra.jwt;

import com.petitjy.threadit.domain.member.entity.Member;
import com.petitjy.threadit.global.util.TimeUtil;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import javax.crypto.SecretKey;
import java.time.LocalDateTime;
import java.util.Date;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtProvider {
    private final JwtProperties jwtProperties;

    private SecretKey key;

    @PostConstruct
    private void setSecretKey() {
        byte[] decode = Decoders.BASE64.decode(jwtProperties.getSecret());
        key = Keys.hmacShaKeyFor(decode);
    }

    public LocalDateTime getAccessTokenExpirationDate() {
        LocalDateTime now = LocalDateTime.now();
        return now.plusSeconds(jwtProperties.getAccessTokenExpireDuration().toSeconds());
    }

    public LocalDateTime getRefreshTokenExpirationDate() {
        LocalDateTime now = LocalDateTime.now();
        return now.plusSeconds(jwtProperties.getRefreshTokenExpireDuration().toSeconds());
    }

    /**
     * @param member
     * @param expirationTime > Use tokenProvider.getXXXTokenExpirationDate
     */
    public String createToken(Member member, LocalDateTime expirationTime) {
        Date expirationDate = TimeUtil.localDateTime2Date(expirationTime);

        return Jwts.builder()
                .issuer("threadit")
                .subject(member.getId().toString())
                .claim("nickname", member.getNickname())
                .claim("role", member.getRole().value())
                .expiration(expirationDate)
                .issuedAt(expirationDate)
                .signWith(key, Jwts.SIG.HS512)  // TODO secret 길이마다 256 384 512 달라지는데 맞는지 검증
                .compact();
    }

    public boolean isValidToken(String token) {

        try {
            Jwts.parser()
                    .verifyWith(key)
                    .build()
                    .parseSignedClaims(token);
            return true;
        } catch (ExpiredJwtException e) {
            // TODO 만료 예외처리
        } catch (JwtException | IllegalArgumentException e) {
            log.error(e.getMessage(), e);
        }
        return false;
    }
}
