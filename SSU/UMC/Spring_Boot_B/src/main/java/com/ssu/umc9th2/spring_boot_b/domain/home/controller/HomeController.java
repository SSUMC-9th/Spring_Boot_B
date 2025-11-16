package com.ssu.umc9th2.spring_boot_b.domain.home.controller;

import com.ssu.umc9th2.spring_boot_b.common.response.ApiResponse;
import com.ssu.umc9th2.spring_boot_b.common.status.SuccessStatus;
import com.ssu.umc9th2.spring_boot_b.domain.home.dto.response.GetHomeResponse;
import com.ssu.umc9th2.spring_boot_b.domain.home.service.HomeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/home")
@Tag(name = "홈")
public class HomeController {

    private final HomeService homeService;

    @GetMapping("/{userId}")
    @Operation(summary = "홈화면 조회", description = "홈화면 조회")
    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "홈화면 조회",content = @Content(schema = @Schema(implementation = GetHomeResponse.class)))
    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "유저가 존재하지 않는 경우",content = @Content())
    public ResponseEntity<ApiResponse<GetHomeResponse>> getAvailableUserMission(
            @PathVariable Long userId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        GetHomeResponse response = homeService.getHome(userId, page, size);
        return ApiResponse.success(SuccessStatus.SUCCESS_200,response);
    }
}
