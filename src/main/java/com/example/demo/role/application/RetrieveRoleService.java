package com.example.demo.role.application;

import com.example.demo.role.api.dto.RoleConverter;
import com.example.demo.role.api.dto.RoleResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 역할 조회 서비스
 */
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class RetrieveRoleService {
    private final RoleService roleService;
    private final RoleConverter roleConverter;

    /**
     * 역할 단건 조회
     *
     * @param roleId 역할식별자
     *
     * @return 역할
     */
    public RoleResponse retrieveRole(Long roleId) {
        return roleConverter.convert(roleService.findById(roleId));
    }

    /**
     * 역할 목록 조회
     *
     * @return 역할 목록
     */
    public List<RoleResponse> retrieveRoleList() {
        return roleService.findAll().stream().map(roleConverter::convert).collect(Collectors.toList());
    }
}
