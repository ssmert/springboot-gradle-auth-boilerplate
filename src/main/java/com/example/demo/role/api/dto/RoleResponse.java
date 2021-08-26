package com.example.demo.role.api.dto;

import com.example.demo.core.dto.BaseResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 역할 응답데이터
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RoleResponse extends BaseResponse {
    private Long roleId;
    private String roleCode;
    private String roleNm;
}

