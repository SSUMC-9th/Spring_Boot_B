package com.ssu.umc9th2.spring_boot_b.domain.user.controller;

import com.ssu.umc9th2.spring_boot_b.common.response.ApiResponse;
import com.ssu.umc9th2.spring_boot_b.common.status.SuccessStatus;
import com.ssu.umc9th2.spring_boot_b.domain.user.dto.response.GetUserMissionStatusResponse;
import com.ssu.umc9th2.spring_boot_b.domain.user.dto.response.GetUserPageResponse;
import com.ssu.umc9th2.spring_boot_b.domain.user.dto.response.GetUserReviewListResponse;
import com.ssu.umc9th2.spring_boot_b.domain.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
@Tag(name = "유저")
public class UserController {

    private final UserService userService;

    @GetMapping("/{userId}")
    @Operation(summary = "유저 페이지 조회", description = "유저 페이지 조회")
    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "유저 페이지 뷰 조회",content = @Content(schema = @Schema(implementation = GetUserPageResponse.class)))
    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "유저가 존재하지 않는 경우",content = @Content())
    public ResponseEntity<ApiResponse<GetUserPageResponse>> getUserPage(@PathVariable Long userId) {
        GetUserPageResponse response = userService.getUserPage(userId);
        return ApiResponse.success(SuccessStatus.SUCCESS_200, response);
    }

    @GetMapping("/{userId}/mission/status")
    @Operation(summary = "유저 미션 상태", description = "유저 미션 상태 조회")
    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "유저 미션 상태 조회",content = @Content(schema = @Schema(implementation = GetUserMissionStatusResponse.class)))
    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "유저가 존재하지 않는 경우",content = @Content())
    public ResponseEntity<ApiResponse<List<GetUserMissionStatusResponse>>> getUserMissionStatus(
            @PathVariable Long userId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ){
        List<GetUserMissionStatusResponse> responses = userService.getUserMissionStatus(userId,page,size).getContent();
        return ApiResponse.success(SuccessStatus.SUCCESS_200, responses);
    }

    @GetMapping("/{userId}/review/list")
    @Operation(summary = "유저 리뷰 목록", description = "유저 리뷰 목록 조회")
    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "유저 리뷰 목록 조회",content = @Content(schema = @Schema(implementation = GetUserReviewListResponse.class)))
    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "유저가 존재하지 않는 경우",content = @Content())
    public ResponseEntity<ApiResponse<GetUserReviewListResponse>> getUserReviewList(
            @PathVariable Long userId,
            @RequestParam(required = false) String restaurantName,
            @RequestParam(required = false) Double rating
    ) {
        GetUserReviewListResponse response = userService.getUserReviewList(userId, restaurantName, rating);
        return ApiResponse.success(SuccessStatus.SUCCESS_200, response);
    }

    @PostMapping("/{userId}/missions/{missionId}")
    @Operation(summary = "유저 미션 등록", description = "유저가 미션을 등록")
    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "201", description = "유저가 미션을 등록",content = @Content())
    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "유저나 미션이 존재하지 않는 경우",content = @Content())
    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "409", description = "유저미션이 존재하는 경우",content = @Content())
    public ResponseEntity<ApiResponse<Void>> createUserMission(
        @PathVariable Long userId,
        @PathVariable Long missionId
    ){
        userService.createUserMission(userId, missionId);
        return ApiResponse.success(SuccessStatus.CREATE_USER_MISSION_SUCCESS);
    }

}
