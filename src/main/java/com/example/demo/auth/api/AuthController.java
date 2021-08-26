package com.example.demo.auth.api;

import com.example.demo.auth.api.dto.AuthRequest;
import com.example.demo.auth.api.dto.AuthResponse;
import com.example.demo.auth.api.dto.TokenRefreshResponse;
import com.example.demo.auth.application.AuthService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 인증 REST 컨트롤러이다.
 */
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;

    @ApiOperation(value = "사용자 인증을 요청한다.", nickname = "auth")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response = AuthResponse.class) })
    @PostMapping(value = "/login")
    public ResponseEntity<AuthResponse> auth(HttpServletRequest request, @RequestBody @Valid AuthRequest authRequest) throws AuthenticationException {
        AuthResponse response = authService.auth(authRequest);
        authService.setConnectInfo(request, response.getUserId());

        log.info("[{}] 님이 로그인 하였습니다.", authRequest.getId());

        return ResponseEntity.ok(response);
    }

    @ApiOperation(value = "토큰을 재발급한다", nickname = "refreshToken")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response = TokenRefreshResponse.class) })
    @PostMapping("/refresh")
    public ResponseEntity<TokenRefreshResponse> refreshToken(@RequestBody @NotNull @NotBlank String refreshKey) {
        TokenRefreshResponse response = authService.refreshToken(refreshKey);
        return ResponseEntity.ok(response);
    }

}
