package com.example.demo.role.api.transferobject;

import com.example.demo.role.domain.Role;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

/**
 * 역할 어그리게이션 TO객체 변환기이다.
 *
 * @author jonghyeon
 */
@Component
public class RoleConverter {
    public RoleResponse convert(Role role) {
        RoleResponse response = new RoleResponse();
        BeanUtils.copyProperties(role, response);
        return response;
    }
}
