package com.example.demo.role.application;


import com.example.demo.role.api.transferobject.RoleConverter;
import com.example.demo.role.api.transferobject.RoleResponse;
import com.example.demo.role.domain.RoleService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 역할 조회 서비스이다.
 *
 * @author jonghyeon
 */
@Service
@Transactional(readOnly = true)
@AllArgsConstructor
public class RetrieveRoleService {
    /**
     * 역할 도메인 서비스
     */
    private final RoleService roleService;

    /**
     * 역할 어그리게이션 <--> TO 객체 변환기
     */
    private final RoleConverter roleConverter;

    /**
     * 역할을 조회한다.
     *
     * @param roleId 역할식별자
     * @return 사용자
     */
    public RoleResponse retrieveRole(String roleId) {
        return roleConverter.convert(roleService.find(roleId));
    }

    /**
     * 역할 목록을 조회한다.
     *
     * @return 역할 목록
     */
    public List<RoleResponse> retrieveRoleList() {
        return roleService.findAll().stream().map(roleConverter::convert).collect(Collectors.toList());
    }
}
