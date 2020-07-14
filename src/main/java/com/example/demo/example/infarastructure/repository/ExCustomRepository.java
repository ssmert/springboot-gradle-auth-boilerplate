package com.example.demo.example.infarastructure.repository;

import com.example.demo.core.infrastructure.constant.YesOrNo;
import com.example.demo.example.api.transferobject.PagingExResponse;

import java.time.LocalDate;

/**
 * 사용자정의 예제 레파지토리이다.
 *
 * @author seonghyun
 */
public interface ExCustomRepository {
    /**
     * 예제 목록을 조회한다.
     *
     * @param exUseYn 예제사용여부
     * @param exTitle 예제제목
     * @param startRegDate 시작등록일자
     * @param endRegDate 종료등록일자
     * @param page 페이지
     * @param size 사이즈
     *
     * @return 예제 목록
     */
    PagingExResponse getExList(YesOrNo exUseYn, String exTitle, LocalDate startRegDate, LocalDate endRegDate, int page, int size);
}
