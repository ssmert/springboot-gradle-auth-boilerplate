package com.example.demo.auth.api;

import com.example.demo.auth.api.transferobject.AuthRequest;
import com.example.demo.auth.api.transferobject.AuthResponse;
import com.example.demo.auth.api.transferobject.AuthUserVo;
import com.example.demo.auth.configuration.JwtTokenUtil;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * 인증에 대한 REST 컨트롤러이다.
 *
 * @author jonghyeon
 */
@Slf4j
@NoArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthController {
    /**
     * 최고관리자 식별자
     */
    private static final String SUPER_ADMIN_ID = "sadmin";

    /**
     * 인증관리자
     */
    @Autowired
    private AuthenticationManager authenticationManager;

    /**
     * 토큰 관리 유틸리티
     */
    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @ApiOperation(value = "사용자 인증을 요청한다.", nickname = "auth")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Success", response = AuthResponse.class)})
    @PostMapping(value = "")
    public ResponseEntity<AuthResponse> auth(HttpServletRequest request, @RequestBody AuthRequest authRequest) throws AuthenticationException {
        // 사용자 인증을 처리한다.
        Authentication auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUserId(), authRequest.getUserPw()));
        SecurityContextHolder.getContext().setAuthentication(auth);
        AuthUserVo user = (AuthUserVo) auth.getPrincipal();

        if (log.isInfoEnabled()) {
            if (SUPER_ADMIN_ID.equals(user.getUserId())) {
                log.info(String.format("[최고관리자]님이 로그인 하였습니다.", user.getUserId()));
            } else {
                log.info(String.format("[%s(%s)] 님이 로그인 하였습니다.", user.getUserNm(), user.getUserId()));
            }
        }

        return ResponseEntity.ok(new AuthResponse(jwtTokenUtil.generateToken(user), user.getUserId(), user.getUserNm(), user.getAuthorities()));
    }
}
