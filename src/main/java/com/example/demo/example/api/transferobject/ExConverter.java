package com.example.demo.example.api.transferobject;

import com.example.demo.example.domain.Ex;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

/**
 * 예제 어그리게이션 TO객체 변환기이다.
 *
 * @author jonghyeon
 */
@Component
public class ExConverter {
    public ExResponse convert(Ex ex) {
        ExResponse response = new ExResponse();
        BeanUtils.copyProperties(ex, response);
        return response;
    }
}
