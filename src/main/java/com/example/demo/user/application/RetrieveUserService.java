package com.example.demo.user.application;

import com.example.demo.user.api.dto.UserConverter;
import com.example.demo.user.api.dto.UserDetailResponse;
import com.example.demo.user.api.dto.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 사용자 조회 서비스
 */
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class RetrieveUserService {
    private final UserService userService;
    private final UserConverter userConverter;

    /**
     * 사용자 단건 조회
     *
     * @param userId 사용자식별자
     *
     * @return 역할
     */
    public UserResponse retrieveUser(Long userId) {
        return userConverter.convert(userService.findById(userId));
    }

    /**
     * 사용자 단건 상세 조회
     *
     * @param userId 사용자식별자
     *
     * @return 역할
     */
    public UserDetailResponse retrieveUserDetail(Long userId) {
        return userConverter.convertDetail(userService.findDetailById(userId));
    }

    /**
     * 사용자 목록 조회
     *
     * @return 사용자 목록
     */
    public List<UserResponse> retrieveUserList() {
        return userService.findAll().stream().map(userConverter::convert).collect(Collectors.toList());
    }


}
