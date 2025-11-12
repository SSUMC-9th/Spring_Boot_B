package com.example.umc9th.app.domain.home.dto;

import com.example.umc9th.app.domain.member.dto.GetAvailableMemberMissionResponse;
import com.example.umc9th.app.domain.member.dto.GetMemberHomeResponse;

import java.util.List;

//프론트와 주고 받을 request나 response를 정의
public record GetHomeResponse(
        GetMemberHomeResponse member,
        List<GetAvailableMemberMissionResponse> missions
) {
}
