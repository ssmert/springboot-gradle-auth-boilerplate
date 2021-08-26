package com.example.demo.core.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 추상화된 최상위 도메인 엔터티이다.
 */
@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor
public abstract class DomainEntity implements Serializable {
    // 최초 등록자
    @CreatedBy
    @Column(name = "REG_ID", length = 36, nullable = false, updatable = false)
    protected String regId;

    // 최초 등록일시
    @CreatedDate
    @Column(name = "REG_DT", nullable = false, updatable = false)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    protected LocalDateTime regDt;

    // 최종 변경자
    @LastModifiedBy
    @Column(name = "UDT_ID", length = 36, nullable = false)
    protected String udtId;

    // 최종 변경일시
    @LastModifiedDate
    @Column(name = "UDT_DT", nullable = false)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    protected LocalDateTime udtDt;

    // 사용여부
    @Setter
    @Column(name = "USE_YN", nullable = false, columnDefinition = "char(1) default 'Y'")
    protected YesOrNo useYn;

    // 삭제여부
    @Setter
    @Column(name = "DEL_YN", nullable = false, columnDefinition = "char(1) default 'N'")
    protected YesOrNo delYn;
}
