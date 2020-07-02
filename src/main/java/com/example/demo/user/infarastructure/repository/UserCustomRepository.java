package com.example.demo.user.infarastructure.repository;


import com.example.demo.core.infrastructure.constant.YesOrNo;
import com.example.demo.user.domain.User;

import java.util.List;

/**
 * 사용자정의 사용자 레파지토리이다.
 *
 * @author jonghyeon
 */
public interface UserCustomRepository {
    /**
     * 사용자 목록을 조회한다.
     *
     * @param userId 사용자식별자
     * @param userUseYn 사용여부
     *
     * @return 사용자목록
     */
    List<User> getUserList(String userId, YesOrNo userUseYn);
}
