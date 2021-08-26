package com.example.demo.menu.domain;

import com.example.demo.core.domain.DomainEntity;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

/**
 * 메뉴 도메인 엔티티
 */
@Entity
@Getter
@Setter
@RequiredArgsConstructor(staticName = "of")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
@Table(name = "TB_MENU", uniqueConstraints = { @UniqueConstraint(name = "U_MENU_CODE", columnNames = { "CODE" }) })
public class Menu extends DomainEntity {
    /**
     * 식별자
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long menuId;

    /**
     * 코드
     */
    @NonNull
    @Column(name = "CODE", length = 100, nullable = false)
    private String menuCode;

    /**
     * 명
     */
    @NonNull
    @Column(name = "NAME", length = 50, nullable = false)
    private String menuNm;

    /**
     * 레벨
     */
    @Column(name = "MENU_LEVEL", nullable = false)
    @NonNull
    private Integer menuLevel;

    /**
     * 순서
     */
    @Column(name = "MENU_ORDER", nullable = false)
    @NonNull
    private Integer menuOrd;
}
