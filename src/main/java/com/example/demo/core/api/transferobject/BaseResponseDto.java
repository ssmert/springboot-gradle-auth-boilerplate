package com.example.demo.core.api.transferobject;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.pojomatic.Pojomatic;

import java.io.Serializable;
import java.util.Date;

/**
 * 추상화된 최상위 응답 DTO이다.
 *
 * @author jonghyeon
 */
@Getter
@Setter
public abstract class BaseResponseDto implements Serializable {
    /**
     * 최초 등록자
     */
    protected String regId;

    /**
     * 최초 등록자명
     */
    protected String regNm;

    /**
     * 최초 등록일시
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    protected Date regDt;

    /**
     * 최총 변경자
     */
    protected String chgId;

    /**
     * 최총 변경자명
     */
    protected String chgNm;
    /**
     * 최종 변경일시
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    protected Date chgDt;

    @Override
    public boolean equals(Object obj) {
        return Pojomatic.equals(this, obj);
    }

    @Override
    public int hashCode() {
        return Pojomatic.hashCode(this);
    }

    @Override
    public String toString() {
        return Pojomatic.toString(this);
    }
}
