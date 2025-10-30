package com.plane.umc9th.domain.mission.entity;

import com.plane.umc9th.domain.member.entity.Member;
import com.plane.umc9th.domain.restaurant.entity.Restaurant;
import com.plane.umc9th.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Builder
@NoArgsConstructor(access= AccessLevel.PROTECTED)
@AllArgsConstructor(access=AccessLevel.PRIVATE)
@Table(name="mission")
public class Mission extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;

    @Column
    private String description;

    @Column(columnDefinition = "DATETIME")
    private LocalDateTime deadline;

    @Column
    private int minAmount;

    @Column
    private int point;

    @Column // 추후에는 인메모리 방식으로 즉석 생성될 듯
    private String verificationCode;

    @Column
    private Boolean isCompleted;
}
