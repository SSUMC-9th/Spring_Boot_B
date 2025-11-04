package com.umc9th.domain.mission.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class HomeMissionDto {

    private Long missionId;     // m.id
    private String storeName;   // s.storeName
    private String title;       // m.title
    private String description; // m.content (Mission 엔티티의 content)
    private Long rewardPoints;  // m.point (Mission 엔티티의 point)
    private LocalDateTime deadline; // m.deadline
}