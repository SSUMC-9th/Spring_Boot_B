package com.example.umc9th.app.domain.member.dto;

import com.example.umc9th.app.domain.mission.enums.MemberMissionStatus;

public record GetMemberMissionResponse(
        Long id,
        Long reward,
        Long cashRequirement,
        String name,
       MemberMissionStatus status
) {
}
