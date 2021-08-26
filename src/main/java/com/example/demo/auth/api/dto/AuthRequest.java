package com.example.demo.auth.api.dto;

import com.example.demo.core.dto.BaseRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 인증 요청 데이터이다.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AuthRequest extends BaseRequest {
    @NotNull
    @NotBlank
    private String id;

    @NotNull
    @NotBlank
    private String password;
}
