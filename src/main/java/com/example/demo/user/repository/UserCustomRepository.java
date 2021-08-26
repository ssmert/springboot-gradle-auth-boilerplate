package com.example.demo.user.repository;

import com.example.demo.user.api.dto.UserResponse;
import com.example.demo.user.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * 커스텀 사용자 레파지토리
 */
public interface UserCustomRepository {
    Page<UserResponse> getUserList(Pageable pageable, String roleCode);

    Optional<User> getUserDetail(long userId);
}
