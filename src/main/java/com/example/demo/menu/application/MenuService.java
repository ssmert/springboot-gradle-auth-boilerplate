package com.example.demo.menu.application;

import com.example.demo.core.domain.Errors;
import com.example.demo.menu.domain.Menu;
import com.example.demo.menu.exception.MenuNotFoundException;
import com.example.demo.menu.repository.MenuRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * 메뉴 도메인 서비스
 */
@Service
@RequiredArgsConstructor
public class MenuService {
    private final MenuRepository menuRepository;

    public Menu findOne(String menuCode) {
        Optional<Menu> menu = menuRepository.findByMenuCode(menuCode);
        return menu.orElseThrow(() -> new MenuNotFoundException(Errors.NOT_FOUND_MENU));
    }

    public List<Menu> findAllByOrderByIdAsc() {
        return menuRepository.findAllByOrderByMenuIdAsc();
    }

    public Set<Menu> findByMenuCodeIn(List<String> menuCodeList) {
        return menuRepository.findByMenuCodeIn(menuCodeList);
    }
}
