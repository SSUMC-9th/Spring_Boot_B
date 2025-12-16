package com.example.umc9th.app.domain.member.dto;

import com.example.umc9th.app.domain.mission.enums.MemberMissionStatus;

public record MemberMissionResponse(
        Long missionId,
        Long reward,
        Long cashRequirement,
        String StoreName,
       MemberMissionStatus status
) {
}
