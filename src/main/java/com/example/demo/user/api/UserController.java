package com.example.demo.user.api;

import com.example.demo.user.api.dto.UserDetailResponse;
import com.example.demo.user.api.dto.UserRequest;
import com.example.demo.user.api.dto.UserResponse;
import com.example.demo.user.application.ChangeUserService;
import com.example.demo.user.application.RetrieveUserService;
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
 * 사용자 컨트롤러
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final RetrieveUserService retrieveUserService;
    private final ChangeUserService changeUserService;

    @ApiOperation(value = "사용자 목록를 조회한다.", nickname = "retrieveUserList")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response = UserResponse.class) })
    @GetMapping(value = "")
    public @ResponseBody
    ResponseEntity<List<UserResponse>> retrieveUserList() {
        List<UserResponse> responses = retrieveUserService.retrieveUserList();
        return responses.isEmpty() ? new ResponseEntity<>(HttpStatus.NO_CONTENT) : new ResponseEntity<>(responses, HttpStatus.OK);
    }

    @ApiOperation(value = "사용자를 조회한다.", nickname = "retrieveUser")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response = UserResponse.class) })
    @GetMapping(value = "/{user-id}")
    public @ResponseBody
    ResponseEntity<UserResponse> retrieveUser(@PathVariable("user-id") Long userId) {
        UserResponse response = retrieveUserService.retrieveUser(userId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @ApiOperation(value = "사용자 상세를 조회한다.", nickname = "retrieveUserDetail")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response = UserDetailResponse.class) })
    @GetMapping(value = "/{user-id}/auth")
    public @ResponseBody
    ResponseEntity<UserDetailResponse> retrieveUserDetail(@PathVariable("user-id") Long userId) {
        UserDetailResponse response = retrieveUserService.retrieveUserDetail(userId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @ApiOperation(value = "사용자를 등록한다.", nickname = "registerUser")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Success") })
    @PostMapping(value = "")
    public @ResponseBody
    HttpEntity<Void> registerUser(@RequestBody @Valid UserRequest userRequest) {
        changeUserService.registerUser(userRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @ApiOperation(value = "사용자를 수정한다.", nickname = "editUser")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Success") })
    @PutMapping(value = "/{user-id}")
    public @ResponseBody
    HttpEntity<Void> editUser(@PathVariable("user-id") Long userId, @RequestBody @Valid UserRequest userRequest) {
        changeUserService.editUser(userId, userRequest);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @ApiOperation(value = "사용자를 삭제한다.", nickname = "deleteUser")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Success") })
    @DeleteMapping(value = "/{user-id}")
    public @ResponseBody
    HttpEntity<Void> deleteUser(@PathVariable("user-id") Long userId) {
        changeUserService.deleteUser(userId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}