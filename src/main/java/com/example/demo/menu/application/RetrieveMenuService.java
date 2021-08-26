package com.example.demo.menu.application;

import com.example.demo.menu.api.dto.MenuConverter;
import com.example.demo.menu.api.dto.MenuResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 메뉴 조회 서비스
 */
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class RetrieveMenuService {
    private final MenuService menuService;
    private final MenuConverter menuConverter;

    /**
     * 메뉴 목록 조회
     *
     * @return 메뉴 목록
     */
    public List<MenuResponse> retrieveMenuList() {
        return menuService.findAllByOrderByIdAsc().stream().map(menuConverter::convert).collect(Collectors.toList());
    }
}
