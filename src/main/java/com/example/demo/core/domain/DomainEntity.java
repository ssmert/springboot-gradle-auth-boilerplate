package com.example.demo.core.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 추상화된 최상위 도메인 엔터티이다.
 *
 * @author jonghyeon
 */
@Getter
@MappedSuperclass
@NoArgsConstructor
public abstract class DomainEntity implements Serializable {

    /**
     * 최초 등록자
     */
    @CreatedBy
    @Column(name = "REG_ID", length = 36, nullable = false, updatable = false)
    protected String regId;

    /**
     * 최초 등록자명
     */
    @CreatedBy
    @Column(name = "REG_NM", length = 50, nullable = false, updatable = false)
    protected String regNm;

    /**
     * 최초 등록일시
     */
    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "REG_DT", nullable = false, updatable = false)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    protected Date regDt;

    /**
     * 최총 변경자
     */
    @LastModifiedBy
    @Column(name = "CHG_ID", length = 36, nullable = false)
    protected String chgId;

    /**
     * 최총 변경자명
     */
    @LastModifiedBy
    @Column(name = "CHG_NM", length = 50, nullable = false)
    protected String chgNm;

    /**
     * 최종 변경일시
     */
    @LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CHG_DT", nullable = false)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    protected Date chgDt;

    /**
     * 플랫폼에 의해 엔티티 신규시 저장 전 호출되어지는 메서드로서 내부적으로 최초등록일시와 최종변경일시를 초기화하고 하위 구현체의 신규 전 초기화 메서드를 호출한다.
     */
    @PrePersist
    private void onPrePersist() {
        // TODO 인증정보가 없으므로 임시로 추가한 로직, 인증적용 후 삭제
        this.regId = "creator";
        this.regNm = "생성자";

        this.chgId = "creator";
        this.chgNm = "변경자";
    }

    /**
     * 플랫폼에 의해 엔티티 변경 저장 전 호출되어지는 메서드로서 내부적으로 최종변경일시를 초기화하고 하위 구현체의 변경 전 초기화 메서드를 호출한다.
     */
    @PreUpdate
    private void onPreUpdate() {
        // TODO 인증정보가 없으므로 임시로 추가한 로직, 인증적용 후 삭제
        this.chgId = "updater";
        this.chgNm = "변경자";
    }

}
