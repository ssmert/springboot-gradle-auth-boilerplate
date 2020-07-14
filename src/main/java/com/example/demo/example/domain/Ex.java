package com.example.demo.example.domain;

import com.example.demo.core.domain.DomainEntity;
import com.example.demo.core.infrastructure.constant.YesOrNo;
import com.example.demo.example.api.transferobject.ExRequest;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * 예제 엔티티이다.
 *
 * @author jonghyeon
 */
@Entity
@Getter
@RequiredArgsConstructor(staticName = "of")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
@Table(name = "TB_EX", uniqueConstraints = { @UniqueConstraint(name = "U_EX_TITLE", columnNames = { "EX_TITLE" }) })
public class Ex extends DomainEntity {
    /**
     * 예제일련번호
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "EX_SEQ")
    private Long exSeq;

    /**
     * 제목
     */
    @NonNull
    @Column(name = "EX_TITLE", nullable = false)
    private String exTitle;

    /**
     * 사용여부
     */
    @NonNull
    @Column(name = "EX_USE_YN", nullable = false, columnDefinition = "char(1)")
    private YesOrNo exUseYn;

    /**
     * 시간
     */
    @NonNull
    @Column(name = "EX_DT")
    private LocalDateTime exDt;

    /**
     * 내용
     */
    @NonNull
    @Column(name = "EX_CTNT", nullable = false)
    private String exCtnt;

    /**
     * 예제를 수정한다.
     *
     * @param req 예제 요청데이터
     */
    public void edit(ExRequest req) {
        this.exTitle = req.getExTitle();
        this.exUseYn = req.getExUseYn();
        this.exDt = req.getExDt();
        this.exCtnt = req.getExCtnt();
    }
}
