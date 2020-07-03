package com.example.demo.user.infarastructure.repository;


import com.example.demo.core.infrastructure.constant.YesOrNo;
import com.example.demo.core.util.CheckUtil;
import com.example.demo.role.domain.QRole;
import com.example.demo.user.domain.QUser;
import com.example.demo.user.domain.User;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.JPQLQuery;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * 사용자정의 사용자 레파지토리 구현체이다.
 *
 * @author jonghyeon
 */
public class UserCustomRepositoryImpl extends QuerydslRepositorySupport implements UserCustomRepository {
    @PersistenceContext
    private EntityManager em;

    public UserCustomRepositoryImpl() {
        super(User.class);
    }

    @Override
    public List<User> getUserList(String userId, YesOrNo userUseYn) {
        QUser qUser = QUser.user;
        QRole qRole = QRole.role;

        BooleanBuilder predicate = new BooleanBuilder();
        if (CheckUtil.isNotNullOrNotEmpty(userId)) {
            predicate.and(qUser.userId.contains(userId));
        }
        if (null != userUseYn) {
            predicate.and(qUser.userUseYn.eq(userUseYn));
        }

        JPQLQuery<User> query = from(qUser);

        query.join(qUser.userRoles, qRole);
        query.where(predicate);
        query.orderBy(qUser.regDt.asc());

        return query.select(qUser).fetchJoin().fetch();
    }
}
