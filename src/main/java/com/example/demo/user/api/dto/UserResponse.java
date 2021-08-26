package com.example.demo.user.api.dto;

import com.example.demo.core.dto.BaseResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 사용자 응답데이터
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse extends BaseResponse {
    private Long userId;
    private String loginId;
    private String userNm;
    private String userPw;
    private String userPhone;
}

