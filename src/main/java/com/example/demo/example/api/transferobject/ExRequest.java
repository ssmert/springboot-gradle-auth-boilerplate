package com.example.demo.example.api.transferobject;

import com.example.demo.core.api.transferobject.BaseRequestDto;
import com.example.demo.core.infrastructure.constant.YesOrNo;
import com.example.demo.core.infrastructure.json.DateYmsDeserializer;
import com.example.demo.core.infrastructure.json.DateYmsSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.pojomatic.annotations.AutoProperty;

import java.time.LocalDateTime;

/**
 * 예제 요청데이터이다.
 *
 * @author jonghyeon
 */
@Getter
@Setter
@AutoProperty
@NoArgsConstructor
@AllArgsConstructor
public class ExRequest extends BaseRequestDto {
    /**
     * 제목
     */
    private String exTitle;

    /**
     * 대상
     */
    private String exTrg;

    /**
     * 사용여부
     */
    private YesOrNo exUseYn;

    /**
     * 예약시간
     */
    @JsonSerialize(using = DateYmsSerializer.class)
    @JsonDeserialize(using = DateYmsDeserializer.class)
    private LocalDateTime exDt;

    /**
     * 내용
     */
    private String exCtnt;
}
