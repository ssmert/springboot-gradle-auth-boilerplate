package com.example.demo.user.api.dto;

import com.example.demo.core.domain.YesOrNo;
import com.example.demo.core.dto.BaseRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.*;

/**
 * 사용자 요청 데이터
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest extends BaseRequest {
    @Pattern(regexp = "[a-zA-Z0-9]{4,255}", message = "4자리 이상의 영문, 숫자만 입력가능합니다.")
    @NotBlank
    private String loginId;

    @Size(max = 50)
    @NotBlank
    private String userNm;

    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-zA-Z])(?=.*[!\"#\\$%&\\(\\)\\*\\+,\\-'./:;<=>\\?@\\[\\\\\\]\\^_`\\{\\|}])[a-zA-Z0-9!\"#\\$%&\\(\\)\\*\\+,\\-'./:;<=>\\?@\\[\\\\\\]\\^_`\\{\\|}]{8,30}$", message = "특수문자, 영문, 숫자 조합으로 8자 이상 30자까지만 사용할 수 있습니다.")
    private String userPw;

    @Pattern(regexp = "[0-9]{10,11}", message = "10, 11자리의 숫자만 입력가능합니다.")
    @NotBlank
    private String userPhone;

    @NotNull
    private YesOrNo useYn;

    @NotNull
    @PositiveOrZero
    private Long roleId;

    @NotNull
    @PositiveOrZero
    private Long departmentId;
}

