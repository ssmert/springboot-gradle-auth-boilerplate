package com.example.demo.user.application;


import com.example.demo.core.infrastructure.constant.Errors;
import com.example.demo.core.infrastructure.exception.IllegalArgException;
import com.example.demo.core.util.CheckUtil;
import com.example.demo.role.domain.Role;
import com.example.demo.role.domain.RoleService;
import com.example.demo.user.api.transferobject.UserRequest;
import com.example.demo.user.domain.User;
import com.example.demo.user.domain.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 사용자 변경 서비스이다.
 *
 * @author jonghyeon
 */
@Service
@Transactional
@AllArgsConstructor
public class ChangeUserService {
    /**
     * 사용자 도메인 서비스
     */
    private final UserService userService;

    /**
     * 역할 도메인 서비스
     */
    private final RoleService roleService;

    /**
     * 사용자를 등록한다.
     *
     * @param req 사용자 요청데이터
     */
    public void registerUser(UserRequest req) {
        if (CheckUtil.isNullOrEmpty(req.getUserId())) {
            throw new IllegalArgException(Errors.UserErrCd.USERS001.getCode(), new Object[]{req.getUserId()});
        }

        User user = User.of(req.getUserId(), req.getUserNm(), req.getUserPwd(), req.getUserPhone(), req.getUserTel(), req.getUserEmail(), req.getUserDept());

        // 역할식별자 목록이 존재하면 역할을 부여한다.
        List<String> roleIdList = req.getRoleIdList();
        if (!roleIdList.isEmpty()) {
            List<Role> roleList = roleService.findByRoleIdIn(roleIdList);
            user.assignRoles(roleList);
        }

        userService.newSave(user);
    }

    /**
     * 사용자를 수정한다.
     *
     * @param userId 사용자식별자
     * @param req    요청데이터
     */
    public void editUser(String userId, UserRequest req) {
        if (CheckUtil.isNullOrEmpty(userId)) {
            throw new IllegalArgException(Errors.UserErrCd.USERS001.getCode(), new Object[]{userId});
        }

        User user = userService.find(userId);
        // 역할식별자 목록이 존재하면 역할을 부여한다.
        List<String> roleIdList = req.getRoleIdList();
        if (!roleIdList.isEmpty()) {
            List<Role> roleList = roleService.findByRoleIdIn(roleIdList);
            user.assignRoles(roleList);
        }

        user.edit(req);
    }

    /**
     * 사용자를 삭제한다.
     *
     * @param userId 사용자식별자
     */
    public void deleteUser(String userId) {
        if (CheckUtil.isNullOrEmpty(userId)) {
            throw new IllegalArgException(Errors.UserErrCd.USERS001.getCode(), new Object[]{userId});
        }

        User user = userService.find(userId);
        userService.delete(user);
    }
}
