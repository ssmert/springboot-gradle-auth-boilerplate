package com.example.demo.role.domain;


import com.example.demo.core.infrastructure.constant.Errors;
import com.example.demo.role.infarastructure.exception.RoleNotFoundException;
import com.example.demo.role.infarastructure.repository.RoleRepository;
import com.example.demo.user.infarastructure.exception.UserDuplicateException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 역할 도메인 서비스이다.
 *
 * @author jonghyeon
 */
@Service
@AllArgsConstructor
public class RoleService {
    /**
     * 역할 도메인 레파지토리
     */
    private final RoleRepository roleRepository;

    /**
     * 역할를 등록한다.
     *
     * @param role 역할
     */
    public void newSave(Role role) {
        checkDuplicate(role.getRoleId());
        roleRepository.save(role);
    }

    /**
     * 동일한 역할이 존재하는지 확인한다.
     *
     * @param roleId 역할식별자
     */
    public void checkDuplicate(String roleId) {
        checkDuplicate(roleId, Errors.RoleErrCd.ROLES003.getCode());
    }

    /**
     * 동일한 역할이 존재하는지 확인한다.
     *
     * @param roleId 역할식별자
     * @param errCd  오류코드
     */
    private void checkDuplicate(final String roleId, String errCd) {
        Role role = roleRepository.findByRoleId(roleId);

        if (null != role) {
            throw new UserDuplicateException(errCd, new Object[]{roleId});
        }
    }


    /**
     * 역할을 조회한다.
     *
     * @param roleId 역할식별자
     * @return 역할
     */
    public Role find(String roleId) {
        return find(roleId, Errors.RoleErrCd.ROLES001.getCode());
    }

    /**
     * 역할을 조회한다.
     *
     * @param roleId 역할식별자
     * @param errCd  에러코드
     * @return 역할
     */
    private Role find(String roleId, String errCd) {
        Role role = roleRepository.findByRoleId(roleId);

        if (null == role) {
            throw new RoleNotFoundException(errCd, new Object[]{roleId});
        }

        return role;
    }

    /**
     * 역할목록을 조회한다.
     *
     * @param roleIdList 역할식별자 목록
     * @return 역할목록
     */
    public List<Role> findByRoleIdIn(List<String> roleIdList) {
        return roleRepository.findByRoleIdIn(roleIdList);
    }

    /**
     * 역할목록을 조회한다.
     *
     * @return 역할목록
     */
    public List<Role> findAll() {
        return roleRepository.findAll();
    }

    /**
     * 역할을를 삭제한다.
     *
     * @param role 역할
     */
    public void delete(Role role) {
        roleRepository.delete(role);
    }
}
