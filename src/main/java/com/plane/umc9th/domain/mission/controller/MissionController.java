package com.plane.umc9th.domain.mission.controller;

import com.plane.umc9th.domain.mission.dto.MissionReqDTO;
import com.plane.umc9th.domain.mission.dto.MissionResDTO;
import com.plane.umc9th.domain.mission.service.MissionService;
import com.plane.umc9th.global.apiPayload.ApiResponse;
import com.plane.umc9th.global.apiPayload.code.GeneralSuccessCode;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/missions")
@RequiredArgsConstructor
public class MissionController {
    private final MissionService missionService;

    @PostMapping()
    public ApiResponse<MissionResDTO.CreateDTO> createMission(
            @RequestBody @Valid MissionReqDTO.CreateMissionDTO dto){
        missionService.createMission(dto);
        return ApiResponse.onSuccess(GeneralSuccessCode.CREATED, null);
    }
}
