package com.example.demo.core.converter;

import com.example.demo.core.domain.YesOrNo;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * ({@link YesOrNo}) 열거형 타입과 문자열 타입간 양방향으로 변환한다.
 */
@Converter(autoApply = true)
public class YesOrNoAttributeConverter implements AttributeConverter<YesOrNo, String> {
    @Override
    public String convertToDatabaseColumn(YesOrNo attribute) {
        return attribute.getCode();
    }

    @Override
    public YesOrNo convertToEntityAttribute(String dbData) {
        return YesOrNo.codeOf(dbData);
    }
}
