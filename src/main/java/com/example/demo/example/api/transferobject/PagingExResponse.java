package com.example.demo.example.api.transferobject;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.pojomatic.annotations.AutoProperty;
import org.springframework.data.domain.Page;

import java.io.Serializable;

/**
 * 페이징 예제 응답데이터이다.
 *
 * @author jonghyeon
 */
@Getter
@Setter
@AutoProperty
@NoArgsConstructor
@AllArgsConstructor
public class PagingExResponse implements Serializable {

    /**
     * 예제 응답데이터 목록
     */
    private Page<ExResponse> exResponses;
}
