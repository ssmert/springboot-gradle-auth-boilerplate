package com.example.demo.auth.api.transferobject;

import com.example.demo.core.api.transferobject.BaseRequestDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.pojomatic.annotations.AutoProperty;

/**
 * 인증 요청 데이터이다.
 *
 * @author jonghyeon
 */
@Getter
@Setter
@AutoProperty
@NoArgsConstructor
@AllArgsConstructor
public class AuthRequest extends BaseRequestDto {
    /**
     * 사용자 식별자
     */
    private String userId;

    /**
     * 사용자 비밀번호
     */
    private String userPw;
}
