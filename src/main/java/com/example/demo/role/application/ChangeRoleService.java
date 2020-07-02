package com.example.demo.role.application;

import com.example.demo.core.infrastructure.constant.Errors;
import com.example.demo.core.infrastructure.exception.IllegalArgException;
import com.example.demo.core.util.CheckUtil;
import com.example.demo.role.api.transferobject.RoleRequest;
import com.example.demo.role.domain.Role;
import com.example.demo.role.domain.RoleService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 역할 변경 서비스이다.
 *
 * @author jonghyeon
 */
@Service
@Transactional
@AllArgsConstructor
public class ChangeRoleService {
    /**
     * 역할 도메인 서비스
     */
    private final RoleService roleService;

    /**
     * 역할를 등록한다.
     *
     * @param req 역할 요청데이터
     */
    public void registerRole(RoleRequest req) {
        if (CheckUtil.isNullOrEmpty(req.getRoleId())) {
            throw new IllegalArgException(Errors.RoleErrCd.ROLES001.getCode(), new Object[]{req.getRoleId()});
        }

        Role role = Role.of(req.getRoleId(), req.getRoleNm(), req.getRoleUseYn());
        roleService.newSave(role);
    }

    /**
     * 역할를 수정한다.
     *
     * @param roleId 역할식별자
     * @param req    요청데이터
     */
    public void editRole(String roleId, RoleRequest req) {
        if (CheckUtil.isNullOrEmpty(roleId)) {
            throw new IllegalArgException(Errors.RoleErrCd.ROLES001.getCode(), new Object[]{roleId});
        }

        Role role = roleService.find(roleId);
        role.edit(req);
    }

    /**
     * 역할를 삭제한다.
     *
     * @param roleId 역할식별자
     */
    public void deleteRole(String roleId) {
        if (CheckUtil.isNullOrEmpty(roleId)) {
            throw new IllegalArgException(Errors.RoleErrCd.ROLES001.getCode(), new Object[]{roleId});
        }

        Role role = roleService.find(roleId);
        roleService.delete(role);
    }
}
