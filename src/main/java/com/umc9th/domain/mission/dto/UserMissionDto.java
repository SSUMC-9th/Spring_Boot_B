package com.umc9th.domain.mission.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor // JPQL의 'new' 키워드를 위해 필수
public class UserMissionDto {

    private Long userMissionId; // um.id
    private Long missionId;     // m.id
    private String storeName;   // s.store_name
    private String title;       // m.title
    private String description; // m.description (SQL의 content에 해당)
    private Long rewardPoints;  // m.point (SQL의 reward_points에 해당)
    private Boolean status;     // um.status (진행 중, 성공 여부)
    private LocalDateTime deadline; // m.deadline
    private LocalDateTime createdAt; // um.created_at
}