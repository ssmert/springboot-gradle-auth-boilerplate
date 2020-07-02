package com.example.demo.user.api;

import com.example.demo.core.infrastructure.constant.YesOrNo;
import com.example.demo.user.api.transferobject.UserRequest;
import com.example.demo.user.api.transferobject.UserResponse;
import com.example.demo.user.application.ChangeUserService;
import com.example.demo.user.application.RetrieveUserService;
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
 * 사용자 REST 컨트롤러이다.
 *
 * @author jonghyeon
 */
@RestController
@AllArgsConstructor
@RequestMapping("/users")
public class UserController {
    /**
     * 사용자 조회 서비스
     */
    private final RetrieveUserService retrieveUserService;

    /**
     * 사용자 변경 서비스
     */
    private final ChangeUserService changeUserService;

    @ApiOperation(value = "사용자 목록을 조회한다.", nickname = "retrieveUserList")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Success", response = UserResponse.class)})
    @GetMapping(value = "")
    public @ResponseBody
    ResponseEntity<List<UserResponse>> retrieveUserList(@RequestParam(value = "userId", required = false, defaultValue = "") String userId,
                                                        @RequestParam(value = "userUseYn", required = false) YesOrNo userUseYn) {
        ResponseEntity<List<UserResponse>> responseEntity;

        // 사용자 목록을 조회한다.
        List<UserResponse> responses = retrieveUserService.retrieveUserList(userId, userUseYn);
        if (responses.isEmpty()) {
            // 응답(내용없음)을 설정한다.
            responseEntity = new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            // 응답(성공)을 설정한다.
            responseEntity = new ResponseEntity<>(responses, HttpStatus.OK);
        }

        return responseEntity;
    }

    @ApiOperation(value = "사용자를 조회한다.", nickname = "retrieveUser")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Success", response = UserResponse.class)})
    @GetMapping(value = "/{user-id}")
    public @ResponseBody
    ResponseEntity<UserResponse> retrieveUser(@PathVariable("user-id") String userId) {
        // 사용자를 조회한다.
        UserResponse response = retrieveUserService.retrieveUser(userId);

        // 응답(성공)을 설정한다.
        ResponseEntity<UserResponse> responseEntity = new ResponseEntity<>(response, HttpStatus.OK);

        return responseEntity;
    }

    @ApiOperation(value = "사용자를 등록한다.", nickname = "registerUser")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Success")})
    @PostMapping(value = "")
    public @ResponseBody
    HttpEntity<Void> registerCh(@RequestBody UserRequest userRequest) {
        // 사용자를 등록한다.
        changeUserService.registerUser(userRequest);

        // 응답(생성)을 설정한다.
        ResponseEntity<Void> responseEntity = new ResponseEntity<>(HttpStatus.CREATED);

        return responseEntity;
    }

    @ApiOperation(value = "사용자를 수정한다.", nickname = "editUser")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Success")})
    @PutMapping(value = "/{user-id}")
    public @ResponseBody
    HttpEntity<Void> editUser(@PathVariable("user-id") String userId, @RequestBody UserRequest userRequest) {
        // 사용자를 수정한다.
        changeUserService.editUser(userId, userRequest);

        // 응답(내용없음)을 설정한다.
        ResponseEntity<Void> responseEntity = new ResponseEntity<>(HttpStatus.NO_CONTENT);

        return responseEntity;
    }

    @ApiOperation(value = "사용자를 삭제한다.", nickname = "deleteUser")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Success")})
    @DeleteMapping(value = "/{user-id}")
    public @ResponseBody
    HttpEntity<Void> deleteUser(@PathVariable("user-id") String userId) {
        // 사용자를 삭제한다.
        changeUserService.deleteUser(userId);

        // 응답(내용없음)을 설정한다.
        ResponseEntity<Void> responseEntity = new ResponseEntity<>(HttpStatus.NO_CONTENT);

        return responseEntity;
    }
}
