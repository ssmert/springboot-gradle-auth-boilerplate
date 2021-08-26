package com.example.demo.menu.api.dto;

import com.example.demo.core.dto.BaseResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 메뉴 응답데이터
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MenuResponse extends BaseResponse {
    private Long menuId;
    private String menuCode;
    private String menuNm;
    private Integer menuLevel;
    private Integer menuOrd;
}

