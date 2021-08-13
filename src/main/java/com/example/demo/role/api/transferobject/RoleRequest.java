package com.example.demo.role.api.transferobject;

import com.example.demo.core.api.transferobject.BaseRequestDto;
import com.example.demo.core.infrastructure.constant.YesOrNo;
import com.google.common.collect.Lists;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.pojomatic.annotations.AutoProperty;

import javax.validation.constraints.NotBlank;
import java.util.List;

/**
 * 역할 요청데이터이다.
 *
 * @author jonghyeon
 */
@Getter
@Setter
@AutoProperty
@NoArgsConstructor
@AllArgsConstructor
public class RoleRequest extends BaseRequestDto {
    /**
     * TODO 벨리데이션 체크 진행할 것
     * @AssertFalse : false 값만 통과 가능
     * @AssertTrue : true 값만 통과 가능
     * @DecimalMax(value=) : 지정된 값 이하의 실수만 통과 가능
     * @DecimalMin(value=) : 지정된 값 이상의 실수만 통과 가능
     * @Digits(integer=,fraction=) : 대상 수가 지정된 정수와 소수 자리수보다 적을 경우 통과 가능
     * @Future : 대상 날짜가 현재보다 미래일 경우만 통과 가능
     * @Past : 대상 날짜가 현재보다 과거일 경우만 통과 가능
     * @Max(value) : 지정된 값보다 아래일 경우만 통과 가능
     * @Min(value) : 지정된 값보다 이상일 경우만 통과 가능
     * @NotNull : null 값이 아닐 경우만 통과 가능
     * @Null : null일 겨우만 통과 가능
     * @Pattern(regex=, flag=) : 해당 정규식을 만족할 경우만 통과 가능
     * @Size(min=, max=) : 문자열 또는 배열이 지정된 값 사이일 경우 통과 가능
     * @Valid : 대상 객체의 확인 조건을 만족할 경우 통과 가능
     */

    /**
     * 역할식별자
     */
    @NotBlank(message = "아이디를 입력해주세요")
    private String roleId;

    /**
     * 역할명
     */
    private String roleNm;

    /**
     * 사용여부
     */
    private YesOrNo roleUseYn;

    /**
     * 역할식별자목록
     */
    private List<String> menuIdList = Lists.newArrayList();
}

