package com.example.demo.auth.api.dto;

import com.example.demo.core.domain.YesOrNo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * 인증 응답 데이터이다.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AuthResponse implements Serializable {
    private String accessToken;
    private String refreshToken;
    private Long userId;
    private YesOrNo useYn;
}
