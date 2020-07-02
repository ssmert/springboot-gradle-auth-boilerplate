package com.example.demo.core.infrastructure.converter;


import com.example.demo.core.infrastructure.constant.YesOrNo;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * ({@link YesOrNo}) 열거형 타입과 문자열 타입간 양방향으로 변환한다.
 *
 * @author jonghyeon
 */
@Converter(autoApply = true)
public class YesOrNoAttributeConverter implements AttributeConverter<YesOrNo, String> {
    @Override
    public String convertToDatabaseColumn(YesOrNo attribute) {
        return (attribute == null ? YesOrNo.Yes.getCode() : attribute.getCode());
    }

    @Override
    public YesOrNo convertToEntityAttribute(String dbData) {
        return (null == dbData ? YesOrNo.Yes : YesOrNo.codeOf(dbData));
    }
}
