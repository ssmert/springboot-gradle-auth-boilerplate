package com.example.demo.core.converter;

import com.example.demo.core.domain.YesOrNo;
import org.springframework.core.convert.converter.Converter;

/**
 * 문자열({@link String})을 ({@link YesOrNo}) 열거형 상수로 변환해주는 SPRING 오브젝트 변환기이다.
 */
public class YesOrNoEnumConverter implements Converter<String, YesOrNo> {
    @Override
    public YesOrNo convert(String value) {
        return YesOrNo.codeOf(value.toUpperCase());
    }
}
