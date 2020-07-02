package com.example.demo.auth.configuration;

import com.example.demo.auth.api.transferobject.AuthUserVo;
import com.google.common.base.Joiner;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * JWT 토큰 처리 유틸리티이다.
 *
 * @author jonghyeon
 */
@Component
public class JwtTokenUtil {
    /**
     * 암호화 키
     */
    @Value("${jwt.token.secret}")
    private String secret;

    /**
     * 토큰의 만료기간
     */
    @Value("${jwt.token.expiration}")
    private Long expiration;

    /**
     * 오리진
     */
    @Value("${jwt.token.origin}")
    private String origin;

    /**
     * 토큰에서 사용자식별자를 추출하여 반환한다.
     *
     * @param token 토큰
     *
     * @return 사용자식별자
     */
    public String getUserIdFromToken(String token) {
        String userId;

        try {
            final Claims claims = this.getClaimsFromToken(token);
            userId = claims.getSubject();
        }
        catch (Exception e) {
            userId = null;
        }

        return userId;
    }

    /**
     * 토큰에서 사용자 역할목록을 추출하여 문자열로 반환한다.
     *
     * @param token 토큰
     *
     * @return 사용자역할목록
     */
    public String getAuthoritiesFromToken(String token) {
        String authorities;

        try {
            final Claims claims = this.getClaimsFromToken(token);
            authorities = (String)claims.get("authorities");
        }
        catch (Exception e) {
            authorities = null;
        }

        return authorities;
    }

    /**
     * 토큰에서 ORIGIN을 추출하여 반환한다.
     *
     * @param token 토큰
     *
     * @return ORIGIN
     */
    public String getOriginFromToken(String token) {
        String origin;

        try {
            final Claims claims = this.getClaimsFromToken(token);
            origin = (String)claims.get("origin");
        }
        catch (Exception e) {
            origin = null;
        }

        return origin;
    }

    /**
     * 토큰의 생성일자를 반환한다.
     *
     * @param token 토큰
     *
     * @return 토큰생성일자
     */
    public Date getCreatedDateFromToken(String token) {
        Date created;
        try {
            final Claims claims = this.getClaimsFromToken(token);
            created = new Date((Long)claims.get("created"));
        }
        catch (Exception e) {
            created = null;
        }
        return created;
    }

    /**
     * 토큰의 만료일자를 반환한다.
     *
     * @param token 토큰
     *
     * @return 토큰만료일자
     */
    public Date getExpirationDateFromToken(String token) {
        Date expiration;
        try {
            final Claims claims = this.getClaimsFromToken(token);
            expiration = claims.getExpiration();
        }
        catch (Exception e) {
            expiration = null;
        }
        return expiration;
    }

    /**
     * 토큰에서 클레임을 추출하여 반환한다.
     *
     * @param token 토큰
     *
     * @return 클레임
     */
    private Claims getClaimsFromToken(String token) {
        Claims claims;

        try {
            claims = Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token).getBody();
        }
        catch (Exception e) {
            claims = null;
        }

        return claims;
    }

    /**
     * 토큰생성일자를 반환한다.
     *
     * @return 토큰생성일자
     */
    private Date generateCurrentDate() {
        return new Date(System.currentTimeMillis());
    }

    /**
     * 토큰만료일자를 expiration을 기준으로 계산하여 반환한다.
     *
     * @return 토큰만료일자
     */
    private Date generateExpirationDate() {
        return new Date(System.currentTimeMillis() + this.expiration * 1000);
    }

    /**
     * 토큰의 만료여부를 반환한다.
     * (토큰의 만료일자를 추출하여 현재일자와 비교하여 만료일자 이전이면 true를 반환한다.)
     *
     * @param token 토큰
     *
     * @return 토큰만료여부
     */
    private Boolean isTokenExpired(String token) {
        final Date expiration = this.getExpirationDateFromToken(token);
        return expiration.before(this.generateCurrentDate());
    }

    /**
     * 사용자 정보를 이용하여 토큰을 생성한다.
     * (사용자식별자, 사용자명, 역할, ORIGIN, 생성일자)
     *
     * @param loginUser 로그인사용자정보
     *
     * @return 암호화된 토큰
     */
    public String generateToken(AuthUserVo loginUser) {
        Map<String, Object> claims = new HashMap<String, Object>();
        claims.put("sub", loginUser.getUserId());
        claims.put("userNm", loginUser.getUserNm());
        claims.put("authorities", Joiner.on(",").join(loginUser.getAuthorities()));
        claims.put("origin", this.origin);
        claims.put("created", this.generateCurrentDate());

        return this.generateToken(claims);
    }

    /**
     * 토큰생성 정보를 가지고 JWT 토큰을 생성하여 반환한다.
     *
     * @param claims 생성정보
     *
     * @return JWT 토큰
     */
    private String generateToken(Map<String, Object> claims) {
        return Jwts.builder().setClaims(claims).setExpiration(this.generateExpirationDate()).signWith(SignatureAlgorithm.HS512, this.secret).compact();
    }

    /**
     * 토큰의 갱신여부를 반환한다.
     *
     * @param token 토큰
     *
     * @return 갱신여부
     */
    public Boolean canTokenBeRefreshed(String token) {
        return !this.isTokenExpired(token);
    }

    /**
     * 토큰을 갱신한다.
     *
     * @param token 토큰
     *
     * @return 갱신된 토큰문자열
     */
    public String refreshToken(String token) {
        String refreshedToken;

        try {
            final Claims claims = this.getClaimsFromToken(token);
            claims.put("created", this.generateCurrentDate());
            refreshedToken = this.generateToken(claims);
        }
        catch (Exception e) {
            refreshedToken = null;
        }

        return refreshedToken;
    }

    /**
     * 토큰의 유효성여부를 반환다.
     * (요청 토큰과 사용자 정보, 만료여부를 확인하여 유효한 토큰인지 확인한다.)
     *
     * @param token 인증토큰
     * @param user 인증사용자
     *
     * @return 토큰유효여부
     */
    public boolean validateToken(String token, AuthUserVo user) {
        boolean isValid = false;

        if (origin.equals(getOriginFromToken(token))) {
            if (user.getUsername().equals(getUserIdFromToken(token))) {
                if (!isTokenExpired(token) && AuthorityUtils.commaSeparatedStringToAuthorityList(getAuthoritiesFromToken(token))
                        .containsAll(null == user.getAuthorities() ? new ArrayList<GrantedAuthority>() : user.getAuthorities())) {
                    isValid = true;
                }
            }
        }
        else {
            if (!isTokenExpired(token)) {
                isValid = true;
            }
        }

        return isValid;
    }
}
