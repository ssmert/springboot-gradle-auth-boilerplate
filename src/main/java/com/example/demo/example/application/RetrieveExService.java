package com.example.demo.example.application;

import com.example.demo.core.infrastructure.constant.YesOrNo;
import com.example.demo.example.api.transferobject.ExConverter;
import com.example.demo.example.api.transferobject.ExResponse;
import com.example.demo.example.api.transferobject.PagingExResponse;
import com.example.demo.example.domain.ExService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

/**
 * 예제 조회 서비스이다.
 *
 * @author jonghyeon
 */
@Service
@Transactional(readOnly = true)
@AllArgsConstructor
public class RetrieveExService {
    /**
     * 예제 도메인 서비스
     */
    private final ExService exService;

    /**
     * 예제 어그리게이션 <--> TO 객체 변환기
     */
    private final ExConverter exConverter;

    /**
     * 예제를 조회한다.
     *
     * @param exSeq 예제 일련번호
     *
     * @return 예제
     */
    public ExResponse retrieveEx(long exSeq) {
        return exConverter.convert(exService.find(exSeq));
    }

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
    public PagingExResponse retrieveExList(YesOrNo exUseYn, String exTitle, LocalDate startRegDate, LocalDate endRegDate, int page, int size) {
        return exService.getExList(exUseYn, exTitle, startRegDate, endRegDate, page, size);
    }
}
