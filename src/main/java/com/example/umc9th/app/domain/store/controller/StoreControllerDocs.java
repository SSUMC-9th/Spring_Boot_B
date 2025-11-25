package com.example.umc9th.app.domain.store.controller;

import com.example.umc9th.app.domain.review.entity.Review;
import com.example.umc9th.app.domain.store.dto.*;
import com.example.umc9th.infra.apiPayload.ApiResponse;
import com.example.umc9th.infra.validator.ValidPage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

public interface StoreControllerDocs {
    @Operation(summary = "가게 정보 생성", description = "새로운 가게를 생성합니다.")
    @ApiResponses({@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "가게 생성 성공", content = @Content(schema = @Schema(implementation = PostCreateStoreResponse.CreateStoreDTO.class))), @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "에러", content = @Content())})
    ApiResponse<PostCreateStoreResponse.CreateStoreDTO> createStore(@RequestBody @Valid PostCreateStoreRequest.CreateStoreDTO dto);

    @Operation(summary = "가게 미션 생성", description = "새로운 가게 미션을 생성합니다.")
    @ApiResponses({@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "가게 미션 생성 성공", content = @Content(schema = @Schema(implementation = PostCreateStoreMissionResponse.CreateStoreMissionDTO.class))), @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "에러", content = @Content())})
    ApiResponse<PostCreateStoreMissionResponse.CreateStoreMissionDTO> createStoreMission(@RequestBody @Valid PostCreateStoreMissionRequest.CreateStoreMissionDTO dto);

    @Operation(summary = "가게 미션 조회", description = "가게 미션을 조회합니다.",parameters = {
            @Parameter(
                    name = "sort",
                    description = "정렬 기준 (기본: updatedAt,desc)",
                    example = "updatedAt,desc"
            )
    })
    @ApiResponses({@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "가게 미션 조회 성공", content = @Content(schema = @Schema(implementation = GetStoreMissionResponse.class))), @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "에러", content = @Content())})
    public ApiResponse<Page<GetStoreMissionResponse>> getStoreMission(@PathVariable Long storeId, @ValidPage @RequestParam(name = "page", defaultValue = "1") Integer page, @RequestParam(name = "size", defaultValue = "10") Integer size);
}
