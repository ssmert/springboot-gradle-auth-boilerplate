package com.example.demo.menu.api;

import com.example.demo.menu.api.dto.MenuResponse;
import com.example.demo.menu.application.RetrieveMenuService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 메뉴 컨트롤러
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/menus")
public class MenuController {
    private final RetrieveMenuService retrieveMenuService;

    @ApiOperation(value = "메뉴 목록을 조회한다.", nickname = "retrieveMenuList")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response = MenuResponse.class) })
    @GetMapping(value = "")
    public @ResponseBody
    ResponseEntity<List<MenuResponse>> retrieveMenuList() {
        List<MenuResponse> responses = retrieveMenuService.retrieveMenuList();
        return responses.isEmpty() ? new ResponseEntity<>(HttpStatus.NO_CONTENT) : new ResponseEntity<>(responses, HttpStatus.OK);
    }
}
