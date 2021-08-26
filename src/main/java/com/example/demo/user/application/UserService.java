package com.example.demo.user.application;

import com.example.demo.auth.exception.UnAuthorizationException;
import com.example.demo.core.domain.Errors;
import com.example.demo.user.domain.User;
import com.example.demo.user.exception.UserDuplicateException;
import com.example.demo.user.exception.UserNotFoundException;
import com.example.demo.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * 사용자 도메인 서비스
 */
@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public void save(User user) {
        checkDuplicate(user.getLoginId());
        userRepository.save(user);
    }

    private void checkDuplicate(final String loginId) {
        Optional<User> user = userRepository.findByLoginId(loginId);
        if (user.isPresent()) {
           throw new UserDuplicateException(Errors.DUPLICATED_USER);
        }
    }

    public User findById(Long userId) {
        Optional<User> user = userRepository.findById(userId);
        return user.orElseThrow(() -> new UserNotFoundException(Errors.NOT_FOUND_USER));
    }
    public User findDetailById(Long userId) {
        Optional<User> user = userRepository.getUserDetail(userId);
        return user.orElseThrow(() -> new UserNotFoundException(Errors.NOT_FOUND_USER));
    }

    public User findByLoginId(String loginId) {
        Optional<User> user = userRepository.findByLoginId(loginId);
        return user.orElseThrow(() -> new UserNotFoundException(Errors.NOT_FOUND_USER));
    }

    public List<User> findAll() {
        return userRepository.findAllByOrderByRegDtDesc();
    }

    public void delete(User user) {
        userRepository.delete(user);
    }

    public User findByLoginIdForAuth(String loginId) {
        Optional<User> user = userRepository.findByLoginId(loginId);
        return user.orElseThrow(() -> new UnAuthorizationException(Errors.LOGIN_FAILED_ID_OR_PW));
    }
}
