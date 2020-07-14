package com.example.demo.example.infarastructure.repository;

import com.example.demo.core.infrastructure.constant.YesOrNo;
import com.example.demo.core.util.CheckUtil;
import com.example.demo.example.api.transferobject.ExConverter;
import com.example.demo.example.api.transferobject.ExResponse;
import com.example.demo.example.api.transferobject.PagingExResponse;
import com.example.demo.example.domain.Ex;
import com.example.demo.example.domain.QEx;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.JPQLQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 사용자정의 예제 레파지토리 구현체이다.
 *
 * @author seonghyun
 */
public class ExCustomRepositoryImpl extends QuerydslRepositorySupport implements ExCustomRepository {
    @PersistenceContext
    private EntityManager em;

    /**
     * 예제 어그리게이션 <--> TO 객체 변환기
     */
    @Autowired
    private ExConverter exConverter;

    public ExCustomRepositoryImpl() {
        super(Ex.class);
    }

    @Override
    public PagingExResponse getExList(YesOrNo exUseYn, String exTitle, LocalDate startRegDate, LocalDate endRegDate, int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);

        QEx qEx = QEx.ex;

        JPQLQuery query = from(qEx);

        // 조회조건 추가
        BooleanBuilder predicate = new BooleanBuilder();
        if (null != exUseYn) {
            predicate.and(qEx.exUseYn.eq(exUseYn));
        }
        if (CheckUtil.isNotNullOrNotEmpty(exTitle)) {
            predicate.and(qEx.exTitle.contains(exTitle));
        }
        if (null != startRegDate && null != endRegDate) {
            LocalDateTime sTime = LocalDateTime.of(startRegDate.getYear(), startRegDate.getMonth(), startRegDate.getDayOfMonth(), 0, 0, 0);
            LocalDateTime eTime = LocalDateTime.of(endRegDate.getYear(), endRegDate.getMonth(), endRegDate.getDayOfMonth(), 23, 59, 59);
            Date start = Date.from(sTime.atZone(ZoneId.systemDefault()).toInstant());
            Date end = Date.from(eTime.atZone(ZoneId.systemDefault()).toInstant());

            predicate.and(qEx.regDt.between(start, end));
        }

        query.where(predicate);

        long total = query.fetchCount();

        query.select(qEx);
        List<Ex> datas = getQuerydsl().applyPagination(pageRequest, query).fetch();
        List<ExResponse> res = datas.stream().map(ex -> exConverter.convert(ex)).collect(Collectors.toList());

        PagingExResponse pRes = new PagingExResponse();
        pRes.setExResponses(new PageImpl<>(res, pageRequest, total));

        return pRes;
    }
}
