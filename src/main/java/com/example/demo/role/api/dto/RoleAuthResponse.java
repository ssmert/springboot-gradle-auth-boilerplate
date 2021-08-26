package com.example.demo.role.api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 역할 메뉴 응답데이터
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RoleAuthResponse {
    private Long menuId;
    private String menuCode;
    private String menuNm;
    private Integer menuLevel;
    private Integer menuOrd;
}

