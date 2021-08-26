package com.example.demo.role.repository;

import com.example.demo.core.repository.DomainRepository;
import com.example.demo.role.domain.Role;

import java.util.List;
import java.util.Optional;

/**
 * 역할 도메인 레파지토리
 */
public interface RoleRepository extends DomainRepository<Role, Long> {
    Optional<Role> findByRoleCode(String roleCode);

    List<Role> findAllByOrderByRoleIdAsc();

    List<Role> findByRoleCodeIn(List<String> roleCodeList);

    List<Role> findByRoleIdIn(List<Long> roleIdList);
}
