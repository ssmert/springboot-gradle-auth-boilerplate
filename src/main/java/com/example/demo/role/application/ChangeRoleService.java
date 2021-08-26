package com.example.demo.role.application;

import com.example.demo.menu.application.MenuService;
import com.example.demo.role.api.dto.RoleRequest;
import com.example.demo.role.domain.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 역할 변경 서비스
 */
@Service
@Transactional
@RequiredArgsConstructor
public class ChangeRoleService {
    private final RoleService roleService;
    private final MenuService menuService;

    /**
     * 역할 등록
     *
     * @param req 요청데이터
     */
    public void registerRole(RoleRequest req) {
        Role role = Role.of(req.getRoleCode(), req.getRoleNm());

        // 역할 권한 할당
        if (!req.getMenuIdList().isEmpty()) {
            role.applyRoleAuthList(this.menuService.findByMenuCodeIn(req.getMenuIdList()));
        }

        roleService.save(role);
    }

    /**
     * 역할 수정
     *
     * @param roleId 역할식별자
     * @param req 요청데이터
     */
    public void editRole(Long roleId, RoleRequest req) {
        Role role = roleService.findById(roleId);

        // 역할 권한 할당
        if (!req.getMenuIdList().isEmpty()) {
            role.applyRoleAuthList(this.menuService.findByMenuCodeIn(req.getMenuIdList()));
        }

        role.edit(req);
    }

    /**
     * 역할 삭제
     *
     * @param roleId 역할식별자
     */
    public void deleteRole(Long roleId) {
        Role role = roleService.findById(roleId);
        roleService.delete(role);
    }
}
