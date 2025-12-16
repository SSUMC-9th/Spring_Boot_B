package com.example.umc9th.domain.user.controller;


import com.example.umc9th.domain.mission.dto.res.GetUserMissionResponse;
import com.example.umc9th.domain.review.dto.UserReviewDto;
import com.example.umc9th.domain.user.dto.UserReqDTO;
import com.example.umc9th.domain.user.dto.UserResDTO;
import com.example.umc9th.domain.user.service.UserCommandService;
import com.example.umc9th.domain.user.service.UserQueryService;
import com.example.umc9th.global.apiPayload.ApiResponse;
import com.example.umc9th.global.apiPayload.code.GeneralSuccessCode;
import com.example.umc9th.global.annotation.PageCheck;
import com.example.umc9th.global.apiPayload.response.PageResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.PageRequest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;



@Validated // 유효성 검사 활성화
@RestController
@RequiredArgsConstructor
@RequestMapping("/users")// 유저 관련 API 경로
@Tag(name="유저")
public class UserController { // 💡 클래스 이름 변경

    private final UserQueryService userQueryService;
    private final UserCommandService userCommandService;

    // [API 1] 내가 작성한 리뷰 목록 조회 (GET /users/{userId}/review-list)
    @Operation(summary = "내가 작성한 리뷰 목록 조회", description = "사용자가 작성한 리뷰 목록을 페이지네이션으로 조회합니다. page는 1부터 시작합니다.")
    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "조회 성공", content = @Content(schema = @Schema(implementation = PageResponse.class)))
    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "페이지 번호가 1 미만인 경우")
    @GetMapping("/{userId}/review-list")
    public ApiResponse<PageResponse<UserReviewDto>> getUserReviewList(
            @PathVariable Long userId,
            // @PageCheck으로 page >= 1 검증
            @RequestParam(defaultValue = "1") @PageCheck Integer page,
            // size 기본값 10 설정
            @RequestParam(defaultValue = "10") Integer size) {

        // Spring Data JPA의 0-base 인덱스를 위해 page - 1 처리
        Pageable pageable = PageRequest.of(page - 1, size);

        // Service 호출
        Page<UserReviewDto> pageResponse = userQueryService.getUserReviewList(userId, pageable);

        // Page<T>를 PageResponse<T> DTO로 변환하여 반환
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, PageResponse.from(pageResponse));
    }
    // [API X] 내가 진행 중인 미션 목록 조회 (GET /users/{userId}/mission-list)
    @Operation(
            summary = "내가 진행 중인 미션 목록 조회",
            description = "사용자가 현재 도전 중인 미션 목록을 페이지네이션으로 조회합니다. page는 1부터 시작합니다."
    )
    @io.swagger.v3.oas.annotations.responses.ApiResponse(
            responseCode = "200",
            description = "조회 성공",
            content = @Content(schema = @Schema(implementation = PageResponse.class))
    )
    @io.swagger.v3.oas.annotations.responses.ApiResponse(
            responseCode = "400",
            description = "페이지 번호가 1 미만인 경우"
    )
    @GetMapping("/{userId}/mission-list")
    public ApiResponse<PageResponse<GetUserMissionResponse>> getUserMissionList(
            @PathVariable Long userId,

            //page는 1부터 시작 + 커스텀 검증 유지
            @RequestParam(defaultValue = "1") @PageCheck Integer page,

            /* size 기본값은 10 */
            @RequestParam(defaultValue = "10") Integer size,

            //진행중(false) / 완료(true)
            @RequestParam(defaultValue = "false") Boolean isCompleted
    ) {
        // Spring Data JPA 0-base 보정
        Pageable pageable = PageRequest.of(page - 1, size);

        // 서비스 호출
        Page<GetUserMissionResponse> pageResponse =
                userQueryService.getUserMissionList(userId, isCompleted, pageable);

        // PageResponse로 감싸서 반환
        return ApiResponse.onSuccess(
                GeneralSuccessCode.OK,
                PageResponse.from(pageResponse)
        );
    }
    // 회원가입
    @PostMapping("/signup")
    @Operation(summary = "회원가입api")
    public ApiResponse<String> join(@RequestBody @Valid UserReqDTO.JoinDTO request) {
        userCommandService.joinUser(request);
        return ApiResponse.onSuccess(GeneralSuccessCode.CREATED, "회원가입 성공!");
    }

    // 로그인
    @PostMapping("/login")
    @Operation(summary = "로그인api")
    public ApiResponse<UserResDTO.LoginDTO> login(
            @RequestBody @Valid UserReqDTO.LoginDTO dto
    ){
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, userQueryService.login(dto));
    }
}
