package com.example.demo.user.repository;

import com.example.demo.core.repository.DomainRepository;
import com.example.demo.user.domain.User;

import java.util.List;
import java.util.Optional;

/**
 * 사용자 도메인 레파지토리이다.
 */
public interface UserRepository extends DomainRepository<User, Long>, UserCustomRepository {
    Optional<User> findByLoginId(String loginId);

    List<User> findAllByOrderByRegDtDesc();
}
