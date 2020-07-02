package com.example.demo.role.api;

import com.example.demo.role.api.transferobject.RoleRequest;
import com.example.demo.role.api.transferobject.RoleResponse;
import com.example.demo.role.application.ChangeRoleService;
import com.example.demo.role.application.RetrieveRoleService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 역할 REST 컨트롤러이다.
 *
 * @author jonghyeon
 */
@RestController
@AllArgsConstructor
@RequestMapping("/roles")
public class RoleController {
    /**
     * 역할 조회 서비스
     */
    private final RetrieveRoleService retrieveRoleService;
    /**
     * 역할 변경 서비스
     */
    private final ChangeRoleService changeRoleService;

    @ApiOperation(value = "역할 목록을 조회한다.", nickname = "retrieveRoleList")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Success", response = RoleResponse.class)})
    @GetMapping(value = "")
    public @ResponseBody
    ResponseEntity<List<RoleResponse>> retrieveRoleList() {
        ResponseEntity<List<RoleResponse>> responseEntity;

        // 역할 목록을 조회한다.
        List<RoleResponse> responses = retrieveRoleService.retrieveRoleList();
        if (responses.isEmpty()) {
            // 응답(내용없음)을 설정한다.
            responseEntity = new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            // 응답(성공)을 설정한다.
            responseEntity = new ResponseEntity<>(responses, HttpStatus.OK);
        }

        return responseEntity;
    }

    @ApiOperation(value = "역할를 조회한다.", nickname = "retrieveRole")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Success", response = RoleResponse.class)})
    @GetMapping(value = "/{role-id}")
    public @ResponseBody
    ResponseEntity<RoleResponse> retrieveRole(@PathVariable("role-id") String roleId) {
        // 역할를 조회한다.
        RoleResponse response = retrieveRoleService.retrieveRole(roleId);

        // 응답(성공)을 설정한다.
        ResponseEntity<RoleResponse> responseEntity = new ResponseEntity<>(response, HttpStatus.OK);

        return responseEntity;
    }

    @ApiOperation(value = "역할를 등록한다.", nickname = "registerRole")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Success")})
    @PostMapping(value = "")
    public @ResponseBody
    HttpEntity<Void> registerCh(@RequestBody RoleRequest roleRequest) {
        // 역할를 등록한다.
        changeRoleService.registerRole(roleRequest);

        // 응답(생성)을 설정한다.
        ResponseEntity<Void> responseEntity = new ResponseEntity<>(HttpStatus.CREATED);

        return responseEntity;
    }

    @ApiOperation(value = "역할를 수정한다.", nickname = "editRole")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Success")})
    @PutMapping(value = "/{role-id}")
    public @ResponseBody
    HttpEntity<Void> editRole(@PathVariable("role-id") String roleId, @RequestBody RoleRequest roleRequest) {
        // 역할를 수정한다.
        changeRoleService.editRole(roleId, roleRequest);

        // 응답(내용없음)을 설정한다.
        ResponseEntity<Void> responseEntity = new ResponseEntity<>(HttpStatus.NO_CONTENT);

        return responseEntity;
    }

    @ApiOperation(value = "역할를 삭제한다.", nickname = "deleteRole")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Success")})
    @DeleteMapping(value = "/{role-id}")
    public @ResponseBody
    HttpEntity<Void> deleteRole(@PathVariable("role-id") String roleId) {
        // 역할를 삭제한다.
        changeRoleService.deleteRole(roleId);

        // 응답(내용없음)을 설정한다.
        ResponseEntity<Void> responseEntity = new ResponseEntity<>(HttpStatus.NO_CONTENT);

        return responseEntity;
    }
}
