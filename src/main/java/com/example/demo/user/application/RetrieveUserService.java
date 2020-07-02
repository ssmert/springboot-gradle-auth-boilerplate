package com.example.demo.user.application;

import com.example.demo.core.infrastructure.constant.YesOrNo;
import com.example.demo.user.api.transferobject.UserConverter;
import com.example.demo.user.api.transferobject.UserResponse;
import com.example.demo.user.domain.User;
import com.example.demo.user.domain.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 사용자 조회 서비스이다.
 *
 * @author jonghyeon
 */
@Service
@Transactional(readOnly = true)
@AllArgsConstructor
public class RetrieveUserService {
    /**
     * 사용자 도메인 서비스
     */
    private final UserService userService;

    /**
     * 사용자 어그리게이션 <--> TO 객체 변환기
     */
    private final UserConverter userConverter;

    /**
     * 인증을 위해 사용자를 조회한다.
     *
     * @param userId 사용자식별자
     * @return 사용자
     */
    public User retrieveUserAuth(String userId) {
        return userService.findForAuth(userId);
    }

    /**
     * 사용자를 조회한다.
     *
     * @param userId 사용자식별자
     * @return 사용자
     */
    public UserResponse retrieveUser(String userId) {
        return userConverter.convert(userService.find(userId));
    }

    /**
     * 사용자 목록을 조회한다.
     *
     * @return 사용자 목록
     */
    public List<UserResponse> retrieveUserList(String userId, YesOrNo userUseYn) {
        return userService.getUserList(userId, userUseYn).stream().map(userConverter::convert).collect(Collectors.toList());
    }
}
