package com.example.demo.user.domain;

import com.example.demo.core.infrastructure.constant.Errors;
import com.example.demo.core.infrastructure.constant.YesOrNo;
import com.example.demo.user.infarastructure.exception.UserDuplicateException;
import com.example.demo.user.infarastructure.exception.UserNotFoundException;
import com.example.demo.user.infarastructure.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 사용자 도메인 서비스이다.
 *
 * @author jonghyeon
 */
@Service
@AllArgsConstructor
public class UserService {
    /**
     * 사용자 도메인 레파지토리
     */
    private final UserRepository userRepository;

    /**
     * 사용자를 등록한다.
     *
     * @param user 사용자
     */
    public void newSave(User user) {
        checkDuplicate(user.getUserId());
        userRepository.save(user);
    }

    /**
     * 사용자를 삭제한다.
     *
     * @param user 사용자
     */
    public void delete(User user) {
        userRepository.delete(user);
    }

    /**
     * 사용자를 조회한다.
     *
     * @param userId 사용자식별자
     * @return 사용자
     */
    public User find(String userId) {
        return find(userId, Errors.UserErrCd.USERS002.getCode());
    }

    /**
     * 인증을 위해 사용자를 조회한다.
     *
     * @param userId 사용자식별자
     * @return 사용자
     */
    public User findForAuth(String userId) {
        return userRepository.findByUserId(userId);
    }

    /**
     * 사용자를 조회한다.
     *
     * @param userId 사용자식별자
     * @param errCd  에러코드
     * @return 사용자
     */
    private User find(String userId, String errCd) {
        User user = userRepository.findByUserId(userId);

        if (null == user) {
            throw new UserNotFoundException(errCd, new Object[]{userId});
        }

        return user;
    }

    /**
     * 동일한 사용자가 존재하는지 확인한다.
     *
     * @param userId 사용자식별자
     */
    public void checkDuplicate(String userId) {
        checkDuplicate(userId, Errors.UserErrCd.USERS003.getCode());
    }

    /**
     * 동일한 사용자가 존재하는지 확인한다.
     *
     * @param userId 사용자식별자
     * @param errCd  오류코드
     */
    private void checkDuplicate(final String userId, String errCd) {
        User user = userRepository.findByUserId(userId);

        if (null != user) {
            throw new UserDuplicateException(errCd, new Object[]{userId});
        }
    }

    // ================================================================

    /**
     * 사용자 목록을 조회한다.
     *
     * @param userId    사용자식별자
     * @param userUseYn 사용여부
     * @return 사용자목록
     */
    public List<User> getUserList(String userId, YesOrNo userUseYn) {
        List<User> userList = userRepository.getUserList(userId, userUseYn);

        return userList;
    }

}
