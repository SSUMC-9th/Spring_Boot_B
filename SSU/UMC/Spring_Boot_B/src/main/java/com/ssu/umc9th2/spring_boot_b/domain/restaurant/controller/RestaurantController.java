package com.ssu.umc9th2.spring_boot_b.domain.restaurant.controller;

import com.ssu.umc9th2.spring_boot_b.common.response.ApiResponse;
import com.ssu.umc9th2.spring_boot_b.common.status.SuccessStatus;
import com.ssu.umc9th2.spring_boot_b.domain.mission.dto.request.CreateMissionRequest;
import com.ssu.umc9th2.spring_boot_b.domain.mission.service.MissionService;
import com.ssu.umc9th2.spring_boot_b.domain.restaurant.dto.response.GetRestaurantListResponse;
import com.ssu.umc9th2.spring_boot_b.domain.restaurant.service.RestaurantService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("restaurant")
@Tag(name = "식당")
public class RestaurantController {

    private final RestaurantService restaurantService;
    private final MissionService missionService;

    @GetMapping("/search")
    @Operation(summary = "식당 검색", description = "다양한 조건들로 식당 검색")
    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200",description = "식당 검색 성공",content = @Content(schema = @Schema(implementation = GetRestaurantListResponse.class)))
    public ResponseEntity<ApiResponse<GetRestaurantListResponse>> getRestaurantListWithFilter(
            @RequestParam(required = false) String region,
            @RequestParam(required = false) String name,
            @RequestParam String orderCondition,
            @RequestParam Integer size,
            @RequestParam Integer page
    ){
        GetRestaurantListResponse response = restaurantService.getRestaurantListWithFilter(region, name, orderCondition, size, page);
        return ApiResponse.success(SuccessStatus.SUCCESS_200, response);
    }

    @PostMapping("/{restaurantId}/missions")
    @Operation(summary = "미션 추가", description = "식당에 미션 추가")
    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "201", description = "미션 등록 성공", content = @Content())
    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "식당이 존재하지 않을 때", content = @Content())
    public ResponseEntity<ApiResponse<Void>> createMission(
        @PathVariable Long restaurantId,
        @RequestBody @Valid CreateMissionRequest request
    ){
        missionService.createMission(restaurantId, request);
        return ApiResponse.success(SuccessStatus.CREATE_MISSION_SUCCESS);
    }

}
