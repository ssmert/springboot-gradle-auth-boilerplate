package com.example.demo.role.application;

import com.example.demo.core.domain.Errors;
import com.example.demo.role.domain.Role;
import com.example.demo.role.exception.RoleDuplicateException;
import com.example.demo.role.exception.RoleNotFoundException;
import com.example.demo.role.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * 역할 도메인 서비스
 */
@Service
@RequiredArgsConstructor
public class RoleService {
    private final RoleRepository roleRepository;

    public void save(Role role) {
        checkDuplicate(role.getRoleCode());
        roleRepository.save(role);
    }

    private void checkDuplicate(final String roleCode) {
        Optional<Role> role = roleRepository.findByRoleCode(roleCode);
        if (role.isPresent()) {
            throw new RoleDuplicateException(Errors.DUPLICATED_ROLE);
        }
    }

    public Role findById(Long roleId) {
        Optional<Role> role = roleRepository.findById(roleId);
        return role.orElseThrow(() -> new RoleNotFoundException(Errors.NOT_FOUND_ROLE));
    }

    public Role findByCode(String roleCode) {
        Optional<Role> role = roleRepository.findByRoleCode(roleCode);
        return role.orElseThrow(() -> new RoleNotFoundException(Errors.NOT_FOUND_ROLE));
    }

    public List<Role> findByRoleCodeIn(List<String> roleCodeList) {
        return roleRepository.findByRoleCodeIn(roleCodeList);
    }

    public List<Role> findByRoleIdIn(List<Long> roleIdList) {
        return roleRepository.findByRoleIdIn(roleIdList);
    }

    public List<Role> findAll() {
        return roleRepository.findAllByOrderByRoleIdAsc();
    }

    public void delete(Role role) {
        roleRepository.delete(role);
    }
}
