package com.example.demo.user.api.dto;

import com.example.demo.core.dto.BaseResponse;
import com.example.demo.role.api.dto.RoleAuthResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * 사용자 상세 응답데이터
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDetailResponse extends BaseResponse {
    private Long userId;
    private String loginId;
    private String userNm;
    private String userPw;
    private String userPhone;
    private String roleCode;
    private String roleNm;
    private List<RoleAuthResponse> roleAuths = new ArrayList<>();
}

