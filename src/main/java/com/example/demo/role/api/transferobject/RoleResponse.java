package com.example.demo.role.api.transferobject;

import com.example.demo.core.api.transferobject.BaseResponseDto;
import com.example.demo.core.infrastructure.constant.YesOrNo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.pojomatic.annotations.AutoProperty;

/**
 * 역할 응답데이터이다.
 *
 * @author jonghyeon
 */
@Getter
@Setter
@AutoProperty
@NoArgsConstructor
@AllArgsConstructor
public class RoleResponse extends BaseResponseDto {
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
}

