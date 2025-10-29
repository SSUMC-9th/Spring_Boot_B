package com.ssu.umc9th2.spring_boot_b.domain.mission.entity;

import com.ssu.umc9th2.spring_boot_b.common.base.BaseEntity;
import com.ssu.umc9th2.spring_boot_b.domain.restaurant.entity.Restaurant;
import jakarta.persistence.*;
import lombok.*;
import java.time.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Mission extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;

    private String content;

    private Long point;

    private String foodName;

    private LocalDateTime deadline;

    private String verificationCode;
}

