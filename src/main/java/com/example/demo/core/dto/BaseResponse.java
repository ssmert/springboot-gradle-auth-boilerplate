package com.example.demo.core.dto;

import com.example.demo.core.domain.YesOrNo;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 최상위 응답 DTO
 */
@Getter
@Setter
public abstract class BaseResponse implements Serializable {
    // 최초 등록자
    protected String regId;
    // 최초 등록일시
    protected LocalDateTime regDt;
    // 최종 변경자
    protected String udtId;
    // 최종 변경일시
    protected LocalDateTime udtDt;
    // 사용여부
    protected YesOrNo useYn;
    // 삭제여부
    protected YesOrNo delYn;
}
