package com.example.umc9th.app.domain.member.entity;

import com.example.umc9th.app.domain.mission.entity.Mission;
import com.example.umc9th.app.domain.mission.enums.MemberMissionStatus;
import com.example.umc9th.infra.entity.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;



@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Table(name = "member_mission")
public class MemberMission extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @Builder.Default
    private MemberMissionStatus memberMissionStatus =  MemberMissionStatus.READY;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "mission_id", nullable = false)
    private Mission mission;

    public void updateStatus(MemberMissionStatus status) {
        this.memberMissionStatus = status;
    }
}

