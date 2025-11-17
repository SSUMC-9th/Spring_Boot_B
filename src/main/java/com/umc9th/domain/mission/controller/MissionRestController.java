package com.example.umc9th.domain.mission.controller;

import com.example.umc9th.domain.mission.dto.req.MissionRequestDto;
import com.example.umc9th.domain.mission.dto.res.MissionResponseDto;
import com.example.umc9th.domain.mission.service.MissionCommandService;
import com.example.umc9th.global.apiPayload.ApiResponse;
import com.example.umc9th.global.apiPayload.code.GeneralSuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/missions")
public class MissionRestController {

    private final MissionCommandService missionCommandService;

    @PostMapping("/{missionId}/challenges")
    public ApiResponse<MissionResponseDto.ChallengeResultDto> challengeMission(
            @PathVariable(name = "missionId") Long missionId,
            @RequestBody MissionRequestDto.ChallengeRequest request
    ) {
        // 로그인 기능이 없으므로 임시로 user id 1번 사용
        Long userId = 1L;

        MissionResponseDto.ChallengeResultDto result = missionCommandService.challengeMission(missionId, userId, request);

        return ApiResponse.onSuccess(GeneralSuccessCode.CREATED, result);
    }
}