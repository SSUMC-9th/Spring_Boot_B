package com.example.umc9th.domain.mission.dto.res;

import lombok.*;

import java.time.LocalDateTime;


public class MissionResponseDto {
    @Builder
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ChallengeResultDto{
        private Long userMissionId;
        private Long missionId;
        private String missionTitle;
        private String status;
        private LocalDateTime createdAt;
    }
}
