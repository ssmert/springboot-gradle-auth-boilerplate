package com.example.demo.example.application;

import com.example.demo.example.api.transferobject.ExRequest;
import com.example.demo.example.domain.Ex;
import com.example.demo.example.domain.ExService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 예제 변경 서비스이다.
 *
 * @author jonghyeon
 */
@Service
@Transactional
@AllArgsConstructor
public class ChangeExService {
    /**
     * 예제 도메인 서비스
     */
    private final ExService exService;

    /**
     * 예제를 등록한다.
     *
     * @param req 예제 요청데이터
     */
    public void registerEx(ExRequest req) {
        // 예제를 등록한다.
        Ex ex = Ex.of(req.getExTitle(), req.getExUseYn(), req.getExDt(), req.getExCtnt());
        exService.newSave(ex);
    }

    /**
     * 예제를 수정한다.
     *
     * @param exSeq 예제 일련번호
     * @param req 요청데이터
     */
    public void editEx(long exSeq, ExRequest req) {
        Ex ex = exService.find(exSeq);
        ex.edit(req);
    }
}
