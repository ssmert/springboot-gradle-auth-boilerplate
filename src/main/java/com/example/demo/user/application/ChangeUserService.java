package com.example.demo.user.application;

import com.example.demo.auth.config.AuthPasswordEncoder;
import com.example.demo.role.application.RoleService;
import com.example.demo.user.api.dto.UserRequest;
import com.example.demo.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

/**
 * 사용자 변경 서비스
 */
@Service
@Transactional
@RequiredArgsConstructor
public class ChangeUserService {
    private final UserService userService;
    private final RoleService roleService;
    private final AuthPasswordEncoder authPasswordEncoder;

    /**
     * 사용자 등록
     *
     * @param req 요청데이터
     */
    public void registerUser(UserRequest req) {
        User user = User.of(req.getLoginId(), req.getUserNm(), authPasswordEncoder.encode(req.getUserPw()), req.getUserPhone());

        // 사용자 역할 할당
        user.setUserRole(this.roleService.findById(req.getRoleId()));

        userService.save(user);
    }

    /**
     * 사용자 수정
     *
     * @param userId 사용자식별자
     * @param req 요청데이터
     */
    public void editUser(Long userId, UserRequest req) {
        User user = userService.findById(userId);

        // 사용자 역할 할당
        user.setUserRole(this.roleService.findById(req.getRoleId()));

        user.edit(req);
    }

    /**
     * 사용자 삭제
     *
     * @param userId 사용자식별자
     */
    public void deleteUser(Long userId) {
        User user = userService.findById(userId);
        userService.delete(user);
    }

    /**
     * 사용자 최근 접속정보 설정
     *
     * @param userId 사용자식별자
     * @param connIp 접속IP
     */
    public void setLstCnnInfo(long userId, String connIp) {
        User user = userService.findById(userId);
        user.setLstCnnInfo(LocalDateTime.now(), connIp);
    }

}
