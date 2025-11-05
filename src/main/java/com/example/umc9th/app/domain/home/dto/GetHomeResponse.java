package com.example.umc9th.app.domain.home.dto;

import com.example.umc9th.app.domain.member.dto.GetAvailableMemberMissionResponse;
import com.example.umc9th.app.domain.member.dto.GetMemberHomeResponse;

import java.util.List;

public record GetHomeResponse(
        GetMemberHomeResponse member,
        List<GetAvailableMemberMissionResponse> missions
) {
}
