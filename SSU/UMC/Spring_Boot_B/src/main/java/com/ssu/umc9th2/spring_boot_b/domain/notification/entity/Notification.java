package com.ssu.umc9th2.spring_boot_b.domain.notification.entity;

import com.ssu.umc9th2.common.base.BaseEntity;
import com.ssu.umc9th2.spring_boot_b.domain.mission.entity.Mission;
import com.ssu.umc9th2.spring_boot_b.domain.restaurant.entity.Restaurant;
import com.ssu.umc9th2.spring_boot_b.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Notification extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    private String category;

    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mission_id")
    private Mission mission;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;

    private String title;

    @Column(nullable = false)
    private Boolean isRead;
}

