package com.example.demo.role.infarastructure.repository;


import com.example.demo.core.domain.DomainRepository;
import com.example.demo.role.domain.Role;

import java.util.List;

/**
 * 역할 도메인 레파지토리이다.
 *
 * @author jonghyeon
 */
public interface RoleRepository extends DomainRepository<Role, Long> {
    Role findByRoleId(String roleId);

    List<Role> findByRoleIdIn(List<String> roleIdList);
}
