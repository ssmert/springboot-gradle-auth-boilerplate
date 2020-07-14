package com.example.demo.example.infarastructure.repository;

import com.example.demo.core.domain.DomainRepository;
import com.example.demo.example.domain.Ex;

/**
 * 예제 도메인 레파지토리이다.
 *
 * @author jonghyeon
 */
public interface ExRepository extends DomainRepository<Ex, Long>, ExCustomRepository {
    Ex findByExSeq(long exSeq);
}