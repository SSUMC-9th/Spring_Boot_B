package com.ssu.umc9th2.spring_boot_b.domain.home.dto.response;

import com.ssu.umc9th2.spring_boot_b.domain.user.dto.response.GetAvailableUserMissionResponse;
import com.ssu.umc9th2.spring_boot_b.domain.user.dto.response.GetUserSummaryResponse;

import java.util.List;

public record GetHomeResponse(
        GetUserSummaryResponse user,
        List<GetAvailableUserMissionResponse> missions
) {
}
