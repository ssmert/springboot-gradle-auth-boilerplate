package com.example.demo.menu.api.dto;

import com.example.demo.menu.domain.Menu;
import com.example.demo.role.api.dto.RoleAuthResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

/**
 * DTO 변환기
 */
@Component
public class MenuConverter {
    public MenuResponse convert(Menu menu) {
        MenuResponse response = new MenuResponse();
        BeanUtils.copyProperties(menu, response);
        return response;
    }

    public RoleAuthResponse convertRoleAuth(Menu menu) {
        RoleAuthResponse response = new RoleAuthResponse();
        BeanUtils.copyProperties(menu, response);
        return response;
    }
}
