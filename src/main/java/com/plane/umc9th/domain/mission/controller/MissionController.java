package com.plane.umc9th.domain.mission.controller;

import com.plane.umc9th.domain.mission.dto.MissionReqDTO;
import com.plane.umc9th.domain.mission.dto.MissionResDTO;
import com.plane.umc9th.domain.mission.exception.code.MissionSuccessCode;
import com.plane.umc9th.domain.mission.service.MissionService;
import com.plane.umc9th.global.apiPayload.ApiResponse;
import com.plane.umc9th.global.apiPayload.code.GeneralSuccessCode;
import com.plane.umc9th.global.validator.ValidPage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/missions")
@RequiredArgsConstructor
public class MissionController {
    private final MissionService missionService;

    @Operation(
            summary = "가게의 미션 목록 조회 API",
            description = "특정 가게의 미션을 모두 조회합니다. 페이지네이션으로 제공합니다."
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "실패")
    })
    @GetMapping()
    public ApiResponse<MissionResDTO.MissionPreViewListDTO> getStoreMissions(
            @ValidPage @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "1") long storeId

    ) {
        return ApiResponse.onSuccess(MissionSuccessCode.FOUND,
                missionService.getStoreMissions(storeId, page-1));
    }


    @Operation(
            summary = "자신의 미션 목록 조회 API",
            description = "자신의 미션를 모두 조회합니다. 페이지네이션으로 제공합니다."
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "실패")
    })
    @GetMapping(value = "my")
    public ApiResponse<MissionResDTO.MissionPreViewListDTO> getMyMissions(
            @ValidPage @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "1") long memberId

    ) {
        return ApiResponse.onSuccess(MissionSuccessCode.FOUND,
                missionService.getMyMissions(memberId, page-1));
    }

    @PostMapping()
    public ApiResponse<MissionResDTO.CreateDTO> createMission(
            @RequestBody @Valid MissionReqDTO.CreateMissionDTO dto){
        missionService.createMission(dto);
        return ApiResponse.onSuccess(GeneralSuccessCode.CREATED, null);
    }
}
