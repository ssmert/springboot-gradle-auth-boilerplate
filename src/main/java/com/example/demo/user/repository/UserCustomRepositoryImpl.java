package com.example.demo.user.repository;

import com.example.demo.menu.domain.QMenu;
import com.example.demo.role.domain.QRole;
import com.example.demo.user.api.dto.UserResponse;
import com.example.demo.user.domain.QUser;
import com.example.demo.user.domain.User;
import com.querydsl.jpa.JPQLQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Optional;

/**
 * 커스텀 사용자 레파지토리 구현체
 */
public class UserCustomRepositoryImpl extends QuerydslRepositorySupport implements UserCustomRepository {
    @PersistenceContext
    private EntityManager em;

    public UserCustomRepositoryImpl() {
        super(User.class);
    }

    @Override
    public Optional<User> getUserDetail(long userId) {
        QUser qUser = QUser.user;
        QRole qRole = QRole.role;
        QMenu qMenu = QMenu.menu;

        JPQLQuery<User> query = from(qUser);

        query.join(qUser.userRoles, qRole).fetchJoin();
        query.join(qRole.roleAuths, qMenu).fetchJoin();

        query.where(qUser.userId.eq(userId));

        return Optional.of(query.select(qUser).fetchOne());
    }

    @Override
    public Page<UserResponse> getUserList(Pageable pageable, String roleCode) {
        //        QUser qUser = QUser.user;
        //        QRole qRole = QRole.role;
        //
        //        BooleanBuilder predicate = new BooleanBuilder();
        //        // 삭제여부
        //        predicate.and(qUser.delYn.eq(YesOrNo.No));
        //
        //        userRoleList.forEach(userRole -> {
        //            if(userRole.contains("MANAGER")) {
        //                if (CheckUtil.isNotNullOrNotEmpty(loginId)) {
        //                    predicate.and(qUser.email.eq(loginId));
        //                    predicate.or(qUser.regId.eq(loginId));
        //                }
        //            }
        //            else if(userRole.contains("USER")) {
        //                if (CheckUtil.isNotNullOrNotEmpty(loginId)) {
        //                    predicate.and(qUser.email.eq(loginId));
        //                }
        //            }
        //        });
        //        if (null != userUseYn) {
        //            predicate.and(qUser.useYn.eq(userUseYn));
        //        }
        //        if (CheckUtil.isNotNullOrNotEmpty(searchLoginId)) {
        //            predicate.and(qUser.email.contains(searchLoginId));
        //        }
        //        if (CheckUtil.isNotNullOrNotEmpty(searchRoleCode)) {
        //            predicate.and(qRole.code.eq(searchRoleCode));
        //        }
        //
        //        JPQLQuery<User> query = from(qUser);
        //
        //        query.leftJoin(qUser.userRoles, qRole);
        //
        //        query.where(predicate);
        //
        //        List<UserResponse> results =
        //                getQuerydsl().applyPagination(pageable, query).select(qUser).distinct().fetchJoin().fetch().stream().map(userConverter::convert)
        //                        .collect(Collectors.toList());
        //
        //        return new PageImpl(results, pageable, query.fetchCount());
        //
        return null;
    }

}
