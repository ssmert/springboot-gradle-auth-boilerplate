package com.example.demo.user.api.dto;

import com.example.demo.menu.api.dto.MenuConverter;
import com.example.demo.role.domain.Role;
import com.example.demo.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

/**
 * DTO 변환기
 */
@RequiredArgsConstructor
@Component
public class UserConverter {
    private final MenuConverter menuConverter;

    public UserResponse convert(User user) {
        UserResponse response = new UserResponse();
        BeanUtils.copyProperties(user, response);
        return response;
    }

    public UserDetailResponse convertDetail(User user) {
        UserDetailResponse response = new UserDetailResponse();
        BeanUtils.copyProperties(user, response);

        Role role = user.getUserRoles().get(0);
        response.setRoleCode(role.getRoleCode());
        response.setRoleNm(role.getRoleNm());
        response.setRoleAuths(role.getRoleAuths().stream().map(menuConverter::convertRoleAuth).collect(Collectors.toList()));

        return response;
    }
}
