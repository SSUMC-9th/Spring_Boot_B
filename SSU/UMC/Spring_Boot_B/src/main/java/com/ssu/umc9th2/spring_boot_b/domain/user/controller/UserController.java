package com.ssu.umc9th2.spring_boot_b.domain.user.controller;

import com.ssu.umc9th2.spring_boot_b.common.annotation.IsPagePositive;
import com.ssu.umc9th2.spring_boot_b.common.response.ApiResponse;
import com.ssu.umc9th2.spring_boot_b.common.response.PageResponse;
import com.ssu.umc9th2.spring_boot_b.common.status.SuccessStatus;
import com.ssu.umc9th2.spring_boot_b.domain.user.dto.response.*;
import com.ssu.umc9th2.spring_boot_b.domain.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;



@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
@Tag(name = "유저")
@Validated
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
    @Operation(summary = "유저 미션 조회", description = "유저 미션 조회")
    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "유저 미션 조회",content = @Content(schema = @Schema(implementation = GetUserMissionListResponse.class)))
    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "page값이 0이하인 경우",content = @Content())
    public ResponseEntity<ApiResponse<PageResponse<GetUserMissionResponse>>> getUserMissionStatus(
            @PathVariable Long userId,
            @IsPagePositive @RequestParam() Integer page,
            @RequestParam(defaultValue = "10") Integer size,
            @RequestParam(defaultValue = "false") Boolean isCompleted
    ){
        Page<GetUserMissionResponse> responses = userService.getUserMissionList(userId,page,size,isCompleted);
        return ApiResponse.success(SuccessStatus.SUCCESS_200, PageResponse.from(responses));
    }

    @PatchMapping("/{userId}/mission/{missionId}status")
    @Operation(summary = "유저 미션 상태 변경", description = "유저 미션 상태 변경")
    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "유저 미션 상태 변경 성공",content = @Content(schema = @Schema(implementation = GetUserMissionListResponse.class)))
    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "page값이 0이하인 경우",content = @Content())
    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "미션이 존재하지 않는 경우",content = @Content())
    public ResponseEntity<ApiResponse<PageResponse<GetUserMissionResponse>>> updateUserMissionStatus(
            @PathVariable Long userId,
            @PathVariable Long missionId,
            @IsPagePositive @RequestParam() Integer page,
            @RequestParam(defaultValue = "true") Boolean status,
            @RequestParam(defaultValue = "10") Integer size
    ){
        userService.updateUserMissionStatus(userId, missionId, status);
        Page<GetUserMissionResponse> responses = userService.getUserMissionList(userId,page,size,status);
        return ApiResponse.success(SuccessStatus.SUCCESS_200, PageResponse.from(responses));
    }

    @GetMapping("/{userId}/review/list")
    @Operation(summary = "유저 리뷰 목록", description = "유저 리뷰 목록 조회")
    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "유저 리뷰 목록 조회",content = @Content(schema = @Schema(implementation = GetUserReviewListResponse.class)))
    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "page값이 0이하인 경우",content = @Content())
    public ResponseEntity<ApiResponse<PageResponse<GetUserReviewResponse>>> getUserReviewList(
            @PathVariable Long userId,
            @IsPagePositive @RequestParam() Integer page,
            @RequestParam(defaultValue = "10") Integer size
    ) {
        Page<GetUserReviewResponse> response = userService.getUserReviewList(userId, PageRequest.of(page-1,size));
        return ApiResponse.success(SuccessStatus.SUCCESS_200, PageResponse.from(response));
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
