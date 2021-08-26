package com.example.demo.role.api;

import com.example.demo.role.api.dto.RoleRequest;
import com.example.demo.role.api.dto.RoleResponse;
import com.example.demo.role.application.ChangeRoleService;
import com.example.demo.role.application.RetrieveRoleService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * 역할 컨트롤러
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/roles")
public class RoleController {
    private final RetrieveRoleService retrieveRoleService;
    private final ChangeRoleService changeRoleService;

    @ApiOperation(value = "역할 목록을 조회한다.", nickname = "retrieveRoleList")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response = RoleResponse.class) })
    @GetMapping(value = "")
    public @ResponseBody
    ResponseEntity<List<RoleResponse>> retrieveRoleList() {
        List<RoleResponse> responses = retrieveRoleService.retrieveRoleList();
        return responses.isEmpty() ? new ResponseEntity<>(HttpStatus.NO_CONTENT) : new ResponseEntity<>(responses, HttpStatus.OK);
    }

    @ApiOperation(value = "역할을 조회한다.", nickname = "retrieveRole")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response = RoleResponse.class) })
    @GetMapping(value = "/{role-id}")
    public @ResponseBody
    ResponseEntity<RoleResponse> retrieveRole(@PathVariable("role-id") Long roleId) {
        RoleResponse response = retrieveRoleService.retrieveRole(roleId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @ApiOperation(value = "역할을 등록한다.", nickname = "registerRole")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Success") })
    @PostMapping(value = "")
    public @ResponseBody
    HttpEntity<Void> registerRole(@RequestBody @Valid RoleRequest roleRequest) {
        changeRoleService.registerRole(roleRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @ApiOperation(value = "역할을 수정한다.", nickname = "editRole")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Success") })
    @PutMapping(value = "/{role-id}")
    public @ResponseBody
    HttpEntity<Void> editRole(@PathVariable("role-id") Long roleId, @RequestBody @Valid RoleRequest roleRequest) {
        changeRoleService.editRole(roleId, roleRequest);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @ApiOperation(value = "역할을 삭제한다.", nickname = "deleteRole")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Success") })
    @DeleteMapping(value = "/{role-id}")
    public @ResponseBody
    HttpEntity<Void> deleteRole(@PathVariable("role-id") Long roleId) {
        changeRoleService.deleteRole(roleId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}