package com.petitjy.threadit.infra.jwt;

import com.petitjy.threadit.domain.member.entity.Member;
import com.petitjy.threadit.domain.member.entity.Role;
import com.petitjy.threadit.domain.oauth.dto.MemberPrincipalDetails;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
@RequiredArgsConstructor
public class JwtResolver {
    private final JwtProperties jwtProperties;

    private SecretKey key;

    @PostConstruct
    public void setSecretKey() {
        byte[] decode = Decoders.BASE64.decode(jwtProperties.getSecret());
        key = Keys.hmacShaKeyFor(decode);
    }

    private Claims getClaims(String token) {
        return Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public Long getMemberId(String token) {
        Claims claims = getClaims(token);
        return Long.parseLong(claims.getSubject());
    }

    public String getMemberNickname(String token) {
        Claims claims = getClaims(token);
        return (String) claims.get("nickname");
    }

    public Role getMemberRole(String token) {
        Claims claims = getClaims(token);
        return Role.valueOf("user");
    }

    public Date getExpirationDate(String token) {
        Claims claims = getClaims(token);
        return claims.getExpiration();
    }

    public Authentication getAuthentication(String token) {
        Claims claims = getClaims(token);

        Member member = Member.builder()
                .id(Long.valueOf(claims.getSubject()))
                .role(Role.valueOf((String) claims.get("role")))
                .nickname((String) claims.get("nickname"))
                .build();

        UserDetails memberPrincipal = new MemberPrincipalDetails(member, null, null);
        return new UsernamePasswordAuthenticationToken(memberPrincipal, null, memberPrincipal.getAuthorities());
    }
}
