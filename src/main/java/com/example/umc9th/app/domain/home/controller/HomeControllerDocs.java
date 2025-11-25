package com.example.umc9th.app.domain.home.controller;

import com.example.umc9th.app.domain.home.dto.GetHomeResponse;
import com.example.umc9th.app.domain.review.entity.Review;
import com.example.umc9th.infra.apiPayload.ApiResponse;
import com.example.umc9th.infra.validator.ValidPage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

public interface HomeControllerDocs {
    @Operation(summary = "도전 가능 미션 조회(페이징)", description = "도전 가능한 새로운 미션을 조회합니다.")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "도전 가능 미션 조회 성공", content = @Content(schema = @Schema(implementation = GetHomeResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "에러", content = @Content())
    })
    ApiResponse<GetHomeResponse> getAvailableMemberMission(
            @PathVariable Long memberId,
            @ValidPage
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size
    );
}
