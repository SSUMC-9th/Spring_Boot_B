package com.example.umc9th.domain.mission.converter;

import com.example.umc9th.domain.mission.dto.res.MissionResponseDto;
import com.example.umc9th.domain.mission.entity.Mission;
import com.example.umc9th.domain.user.entity.User;
import com.example.umc9th.domain.user.entity.UserMission;

public class MissionConverter {

    public static UserMission toUserMission(User user, Mission mission) {
        return UserMission.builder()
                .user(user)
                .mission(mission)
                .status(false) // false: 진행 중 (도전 시작)
                .build();
    }

    public static MissionResponseDto.ChallengeResultDto toChallengeResultDto(UserMission userMission) {
        return MissionResponseDto.ChallengeResultDto.builder()
                .userMissionId(userMission.getId())
                .missionId(userMission.getMission().getId())
                .missionTitle(userMission.getMission().getTitle())
                .status("도전 중")
                .createdAt(userMission.getCreatedAt())
                .build();
    }
}