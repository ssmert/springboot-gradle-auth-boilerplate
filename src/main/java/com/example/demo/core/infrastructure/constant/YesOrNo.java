package com.example.demo.core.infrastructure.constant;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 여부에 대한 열거형 상수이다.
 *
 * @author jonghyeon
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum YesOrNo {
    /**
     * 여부의 여
     */
    Yes("Y", "여"),

    /**
     * 여부의 부
     */
    No("N", "부");

    /**
     * 코드
     */
    private String code;

    /**
     * 제목
     */
    private String title;

    /**
     * 여부에 해당되는 열거형 상수를 반환한다.
     *
     * @param code 코드
     * @return 코드에 해당되는 열거형 상수
     */
    public final static YesOrNo codeOf(boolean code) {
        return (code ? YesOrNo.Yes : YesOrNo.No);
    }

    /**
     * 코드에 해당되는 열거형 상수를 반환한다.
     *
     * @param code 코드
     * @return 코드에 해당되는 열거형 상수
     */
    public final static YesOrNo codeOf(String code) {
        for (YesOrNo em : values()) {
            if (em.getCode().equals(code)) {
                return em;
            }
        }

        return null;
    }

    @JsonValue
    @Override
    public String toString() {
        return this.getCode();
    }
}
