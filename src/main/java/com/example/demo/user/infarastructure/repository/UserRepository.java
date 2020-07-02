package com.example.demo.user.infarastructure.repository;


import com.example.demo.core.domain.DomainRepository;
import com.example.demo.user.domain.User;

/**
 * 사용자 도메인 레파지토리이다.
 *
 * @author jonghyeon
 */
public interface UserRepository extends DomainRepository<User, Long>, UserCustomRepository {
    User findByUserId(String userId);
}
