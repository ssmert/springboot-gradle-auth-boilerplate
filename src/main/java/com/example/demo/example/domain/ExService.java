package com.example.demo.example.domain;

import com.example.demo.core.infrastructure.constant.Errors;
import com.example.demo.core.infrastructure.constant.YesOrNo;
import com.example.demo.example.api.transferobject.PagingExResponse;
import com.example.demo.example.infarastructure.exception.ExDuplicateException;
import com.example.demo.example.infarastructure.exception.ExNotFoundException;
import com.example.demo.example.infarastructure.repository.ExRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

/**
 * 예제 도메인 서비스이다.
 *
 * @author jonghyeon
 */
@Service
@AllArgsConstructor
public class ExService {
    /**
     * 예제 도메인 레파지토리
     */
    private final ExRepository exRepository;

    /**
     * 예제를 등록한다.
     *
     * @param ex 예제
     */
    public void newSave(Ex ex) {
        checkDuplicate(ex.getExSeq());
        exRepository.save(ex);
    }

    /**
     * 예제를 조회한다.
     *
     * @param exSeq 예제 일련번호
     *
     * @return 예제
     */
    public Ex find(long exSeq) {
        return find(exSeq, Errors.ExErrCd.EXS001.getCode());
    }

    /**
     * 예제를 조회한다.
     *
     * @param exSeq 예제 일련번호
     * @param errCd 에러코드
     *
     * @return 예제
     */
    private Ex find(long exSeq, String errCd) {
        Ex ex = exRepository.findByExSeq(exSeq);

        if (null == ex) {
            throw new ExNotFoundException(errCd, new Object[] { exSeq });
        }

        return ex;
    }

    /**
     * 동일한 예제가 존재하는지 확인한다.
     *
     * @param exSeq 예제 일련번호
     */
    public void checkDuplicate(long exSeq) {
        checkDuplicate(exSeq, Errors.ExErrCd.EXS002.getCode());
    }

    /**
     * 동일한 예제가 존재하는지 확인한다.
     *
     * @param exSeq 예제 일련번호
     * @param errCd 오류코드
     */
    private void checkDuplicate(final long exSeq, String errCd) {
        Ex ex = exRepository.findByExSeq(exSeq);

        if (null != ex) {
            throw new ExDuplicateException(errCd, new Object[] { exSeq });
        }
    }

    // ================================================================

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
    public PagingExResponse getExList(YesOrNo exUseYn, String exTitle, LocalDate startRegDate, LocalDate endRegDate, int page, int size) {
        return exRepository.getExList(exUseYn, exTitle, startRegDate, endRegDate, page, size);
    }
}
