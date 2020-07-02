package com.example.demo.role.api.transferobject;

import com.example.demo.core.api.transferobject.BaseRequestDto;
import com.example.demo.core.infrastructure.constant.YesOrNo;
import com.google.common.collect.Lists;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.pojomatic.annotations.AutoProperty;

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
     * 역할식별자
     */
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

