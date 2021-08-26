package com.example.demo.menu.repository;

import com.example.demo.core.repository.DomainRepository;
import com.example.demo.menu.domain.Menu;

import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * 메뉴 도메인 레파지토리
 */
public interface MenuRepository extends DomainRepository<Menu, Long> {
    Optional<Menu> findByMenuCode(String menuCode);

    List<Menu> findAllByOrderByMenuIdAsc();

    Set<Menu> findByMenuCodeIn(List<String> menuCodeList);

}
