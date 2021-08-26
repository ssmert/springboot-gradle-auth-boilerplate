package com.example.demo.role.api.dto;

import com.example.demo.role.domain.Role;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

/**
 * DTO 변환기
 */
@Component
public class RoleConverter {
    public RoleResponse convert(Role role) {
        RoleResponse response = new RoleResponse();
        BeanUtils.copyProperties(role, response);
        return response;
    }
}
