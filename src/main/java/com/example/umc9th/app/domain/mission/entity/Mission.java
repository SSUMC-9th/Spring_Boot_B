package com.example.umc9th.app.domain.mission.entity;

import com.example.umc9th.app.domain.member.entity.Member;
import com.example.umc9th.infra.entity.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;


@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Table(name = "mission")
public class Mission extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long cashRequirement;

    @Column(nullable = false)
    private Long verifyNumber;

    @Column(nullable = false)
    private Long reward;

    @OneToMany(mappedBy = "mission", orphanRemoval = true)
    @Builder.Default
    private List<MemberMission> participants = new ArrayList<>();
}

