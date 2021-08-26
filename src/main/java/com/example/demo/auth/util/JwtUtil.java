package com.example.demo.auth.util;

import com.google.common.base.Joiner;
import com.example.demo.auth.api.dto.AuthUser;
import com.example.demo.auth.api.dto.JwtVo;
import com.example.demo.auth.exception.UnAuthorizationException;
import com.example.demo.core.domain.Errors;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Date;

/**
 * Jwt 유틸
 */
@Component
public class JwtUtil {
    @Value("${jwt.token.secret}")
    private String secret;

    private final static long ACCESS_TOKEN_EXPIRATION = 60 * 30 * 1000L; // 30분
    public final static long REFRESH_TOKEN_EXPIRATION = 60 * 60 * 24 * 7 * 1000L; // 1주

    /**
     * 현재 일시 반환
     *
     * @return 현재 일시
     */
    private Date getNowDate() {
        return new Date(System.currentTimeMillis());
    }

    /**
     * 엑세스 토큰 생성
     *
     * @param authUser 인증 사용자
     *
     * @return 엑세스 토큰
     */
    public String generateAccessToken(AuthUser authUser) {
        Claims claims = Jwts.claims();
        claims.put("authorities", Joiner.on(",").join(authUser.getAuthorities()));

        return generateToken(authUser, claims, true);
    }

    /**
     * 리프레시 토큰 생성
     *
     * @param authUser 인증 사용자
     *
     * @return 리프레시 토큰
     */
    public String generateRefreshToken(AuthUser authUser) {
        Claims claims = Jwts.claims();

        return generateToken(authUser, claims, false);
    }

    /**
     * 토큰 생성
     *
     * @param authUser 인증 사용자
     * @param claims 페이로드 데이터
     * @param isAccess 엑세스 토큰 여부
     *
     * @return 토큰
     */
    private String generateToken(AuthUser authUser, Claims claims, boolean isAccess) {
        claims.put("userId", authUser.getUserId());
        claims.put("loginId", authUser.getUsername());

        return Jwts.builder().setClaims(claims) // 데이터
                .setSubject("demo") // 용도
                .setIssuedAt(getNowDate()) // 토큰 생성일
                .setExpiration(new Date(System.currentTimeMillis() + (isAccess ? ACCESS_TOKEN_EXPIRATION : REFRESH_TOKEN_EXPIRATION))) // 토큰 만료일
                .signWith(SignatureAlgorithm.HS512, this.secret) // 사인
                .compact();
    }

    /**
     * 토큰 검증 및 데이터 반환
     *
     * @param token 토큰
     *
     * @return 토큰 데이터
     */
    public JwtVo validateToken(String token) {
        Claims claims;
        Collection<GrantedAuthority> grantedAuthorities;
        JwtVo JwtVo = null;
        try {
            claims = Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token).getBody();
            grantedAuthorities = AuthorityUtils.commaSeparatedStringToAuthorityList((String)claims.get("authorities"));
            JwtVo = new JwtVo(Long.parseLong(claims.get("userId").toString()), (String)claims.get("loginId"),
                    // 만료 여부
                    claims.getExpiration().before(getNowDate()),
                    // 권한
                    grantedAuthorities);
        }
        catch (Throwable t) {
            if (t instanceof ExpiredJwtException) {
                throw new UnAuthorizationException(Errors.EXPIRED_TOKEN);
            }
            else {
                throw new UnAuthorizationException(Errors.INVALID_TOKEN);
            }
        }

        return JwtVo;
    }

    public String refreshAccessToken(AuthUser authUser, String refreshToken) {
        Claims claims;
        String accessToken = null;
        try {
            claims = Jwts.parser().setSigningKey(this.secret).parseClaimsJws(refreshToken).getBody();
            long userId = Long.parseLong(claims.get("userId").toString());

            // 아이디 비교, 리프레시 토큰 만료 확인
            if (authUser.getUserId() == userId && claims.getExpiration().before(getNowDate())) {
                accessToken = this.generateAccessToken(authUser);
            }
            else {
                throw new ExpiredJwtException(null, claims, "");
            }
        }
        catch (Throwable t) {
            if (t instanceof ExpiredJwtException) {
                throw new UnAuthorizationException(Errors.EXPIRED_ALL_TOKEN);
            }
            else {
                throw new UnAuthorizationException(Errors.INVALID_TOKEN);
            }
        }

        return accessToken;

    }

}