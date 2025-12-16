package com.umc9th.domain.store.controller;

import com.umc9th.domain.mission.dto.res.StoreMissionDto;
import com.umc9th.domain.mission.service.MissionQueryService;
import com.umc9th.global.annotation.ExistStore;
import com.umc9th.global.annotation.PageCheck;
import com.umc9th.global.apiPayload.ApiResponse;
import com.umc9th.global.apiPayload.code.GeneralSuccessCode;
import com.umc9th.global.apiPayload.response.PageResponse;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/stores")
public class StoreMissionController {

    // 조회 전용 서비스로 변경
    private final MissionQueryService missionQueryService;

    // [API 2] 특정 가게의 미션 목록 조회 (Paging 적용)
    @Operation(
            summary = "특정 가게의 미션 목록 조회 (Paging)",
            description = "가게 ID로 미션 목록을 페이지네이션 형태로 조회합니다. page는 1부터 시작합니다."
    )
    @io.swagger.v3.oas.annotations.responses.ApiResponse(
            responseCode = "200",
            description = "조회 성공",
            content = @Content(schema = @Schema(implementation = PageResponse.class))
    )
    @io.swagger.v3.oas.annotations.responses.ApiResponse(
            responseCode = "404",
            description = "존재하지 않는 가게 ID"
    )
    @GetMapping("/{storeId}/missions")
    public ApiResponse<PageResponse<StoreMissionDto>> getStoreMissions(
            @PathVariable("storeId") @ExistStore Long storeId,
            @RequestParam(defaultValue = "1") @PageCheck Integer page,
            @RequestParam(defaultValue = "10") Integer size
    ) {

        // Pageable 생성 (Spring Page는 0부터 시작하니까 -1)
        Pageable pageable = PageRequest.of(page - 1, size);

        // 서비스 호출 (static 아님)
        Page<StoreMissionDto> resultPage =
                missionQueryService.getStoreMissions(storeId, pageable);

        // PageResponse 래핑 후 반환
        return ApiResponse.onSuccess(
                GeneralSuccessCode.OK,
                PageResponse.from(resultPage)
        );
    }
}
