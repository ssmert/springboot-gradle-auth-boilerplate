package com.example.demo.menu.api.dto;

import com.example.demo.core.dto.BaseRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 메뉴 요청데이터
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MenuRequest extends BaseRequest {
    private String menuCode;
    private String menuNm;
    private Integer menuLevel;
    private Integer menuOrd;
}

