package com.ssu.umc9th2.spring_boot_b.domain.user.controller;

import com.ssu.umc9th2.spring_boot_b.common.response.ApiResponse;
import com.ssu.umc9th2.spring_boot_b.common.status.SuccessStatus;
import com.ssu.umc9th2.spring_boot_b.domain.user.dto.response.GetUserMissionStatusResponse;
import com.ssu.umc9th2.spring_boot_b.domain.user.dto.response.GetUserPageResponse;
import com.ssu.umc9th2.spring_boot_b.domain.user.dto.response.GetUserReviewListResponse;
import com.ssu.umc9th2.spring_boot_b.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @GetMapping("/{userId}")
    public ResponseEntity<ApiResponse<GetUserPageResponse>> getUserPage(@PathVariable Long userId) {
        GetUserPageResponse response = userService.getUserPage(userId);
        return ApiResponse.success(SuccessStatus.SUCCESS_200, response);
    }

    @GetMapping("/mission/status/{userId}")
    public ResponseEntity<ApiResponse<List<GetUserMissionStatusResponse>>> getUserMissionStatus(
            @PathVariable Long userId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ){
        List<GetUserMissionStatusResponse> responses = userService.getUserMissionStatus(userId,page,size).getContent();
        return ApiResponse.success(SuccessStatus.SUCCESS_200, responses);
    }

    @GetMapping("/review/list/{userId}")
    public ResponseEntity<ApiResponse<GetUserReviewListResponse>> getUserReviewList(
            @PathVariable Long userId,
            @RequestParam(required = false) String restaurantName,
            @RequestParam(required = false) Double rating
    ) {
        GetUserReviewListResponse response = userService.getUserReviewList(userId, restaurantName, rating);
        return ApiResponse.success(SuccessStatus.SUCCESS_200, response);
    }

}
