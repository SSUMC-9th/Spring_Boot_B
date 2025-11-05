package com.example.umc9th.app.domain.member.entity;

import com.example.umc9th.app.domain.member.enums.AlarmType;
import com.example.umc9th.infra.entity.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Table(name = "alarm")
public class Alarm extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "alarm_type", nullable = false)
    private AlarmType alarmType;

    @Column(length = 10)
    private String title;

    @Column(length =100)
    private String content;

    @Column(name = "read_at")
    private LocalDateTime readAt;

    @Column(name = "alarm_count")
    private Long alarmCount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

}
