package com.ssu.umc9th2.spring_boot_b.domain.user.entity;

import com.ssu.umc9th2.spring_boot_b.common.base.BaseEntity;
import com.ssu.umc9th2.spring_boot_b.domain.mission.entity.Mission;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class UserMission extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mission_id")
    private Mission mission;

    @Column(nullable = false)
    private Boolean isCompleted;

    public void updateIsCompleted(Boolean isCompleted) {
        this.isCompleted = isCompleted;
    }
}

