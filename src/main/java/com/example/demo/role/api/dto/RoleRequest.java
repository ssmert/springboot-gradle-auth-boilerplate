package com.example.demo.role.api.dto;

import com.google.common.collect.Lists;
import com.example.demo.core.domain.YesOrNo;
import com.example.demo.core.dto.BaseRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * 역할 요청데이터
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RoleRequest extends BaseRequest {
    @Size(max = 100)
    @NotBlank
    private String roleCode;

    @Size(max = 50)
    @NotBlank
    private String roleNm;

    @NotNull
    private YesOrNo useYn;

    private List<String> menuIdList = Lists.newArrayList();
}

