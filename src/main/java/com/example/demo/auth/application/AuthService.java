package com.example.demo.auth.application;

import com.example.demo.auth.api.dto.AuthRequest;
import com.example.demo.auth.api.dto.AuthResponse;
import com.example.demo.auth.api.dto.AuthUser;
import com.example.demo.auth.api.dto.TokenRefreshResponse;
import com.example.demo.auth.util.JwtUtil;
import com.example.demo.core.util.CommonUtil;
import com.example.demo.core.util.RedisUtil;
import com.example.demo.user.application.ChangeUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
 * 인증 서비스
 */
@Service
@RequiredArgsConstructor
public class AuthService {
    @Value("${custom.title}")
    private String keyPrefix;

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final RedisUtil redisUtil;
    private final ChangeUserService changeUserService;

    /**
     * 사용자 인증
     *
     * @param req 요청 데이터
     *
     * @return 인증 응답 데이터
     */
    public AuthResponse auth(AuthRequest req) {
        // 컨텍스트에 인증 정보 설정
        Authentication auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(req.getId(), req.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(auth);
        AuthUser authUser = (AuthUser)auth.getPrincipal();

        // 레디스에 리프레시 토큰 저장
        String tokenKey = keyPrefix + "_" + authUser.getUserId();
        redisUtil.setData(tokenKey, jwtUtil.generateRefreshToken(authUser), JwtUtil.REFRESH_TOKEN_EXPIRATION);

        return new AuthResponse(jwtUtil.generateAccessToken(authUser), tokenKey, authUser.getUserId(), authUser.getUseYn());
    }

    /**
     * 사용자 최근 접속정보 설정
     *
     * @param request 요청 데이터
     * @param userId 사용자식별자
     */
    public void setConnectInfo(HttpServletRequest request, long userId) {
        // 접속 IP를 구한다.
        String connIp = CommonUtil.getIp(request);

        // 사용자 접속정보를 설정한다.
        changeUserService.setLstCnnInfo(userId, connIp);
    }

    /**
     * 토큰 재발급
     *
     * @param refreshKey 리프레시 키
     *
     * @return 응답 데이터
     */
    public TokenRefreshResponse refreshToken(String refreshKey) {
        AuthUser authUser = (AuthUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String accessToken = jwtUtil.refreshAccessToken(authUser, redisUtil.getData(refreshKey));
        return new TokenRefreshResponse(accessToken);
    }
}
