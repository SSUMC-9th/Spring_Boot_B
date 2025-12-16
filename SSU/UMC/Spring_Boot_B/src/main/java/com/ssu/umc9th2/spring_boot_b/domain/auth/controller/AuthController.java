package com.ssu.umc9th2.spring_boot_b.domain.auth.controller;

import com.ssu.umc9th2.spring_boot_b.common.response.ApiResponse;
import com.ssu.umc9th2.spring_boot_b.common.status.SuccessStatus;
import com.ssu.umc9th2.spring_boot_b.domain.auth.dto.request.LoginRequest;
import com.ssu.umc9th2.spring_boot_b.domain.auth.dto.response.LoginResponse;
import com.ssu.umc9th2.spring_boot_b.domain.auth.dto.request.SignupRequest;
import com.ssu.umc9th2.spring_boot_b.domain.auth.dto.response.ReissueAccessTokenResponse;
import com.ssu.umc9th2.spring_boot_b.domain.auth.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
@Tag(name = "Auth")
public class AuthController {

    private final AuthService authService;


//    @PostMapping("/login")
//    @Operation(summary = "로그인", description = "이메일/비밀번호 기반 세션 로그인")
//    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "로그인 성공", content = @Content(schema = @Schema(implementation = LoginResponse.class)))
//    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "401", description = "이메일 또는 비밀번호 오류", content = @Content())
//    public ResponseEntity<ApiResponse<LoginResponse>> login(
//            @RequestBody @Valid LoginRequest request,
//            HttpServletRequest httpRequest
//    ) {
//        LoginResponse response = authService.login(request);
//        httpRequest.getSession(true);
//
//        return ApiResponse.success(SuccessStatus.LOGIN_SUCCESS, response);
//    }

    @PostMapping("/login")
    @Operation(summary = "로그인")
    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "로그인 성공", content = @Content(mediaType = "application/json", schema = @Schema(implementation = LoginResponse.class)))
    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "401", description = "비밀번호가 일치하지 않는 경우", content = @Content)
    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "유저가 존재하지 않는 경우", content = @Content)
    public ResponseEntity<ApiResponse<LoginResponse>> login(
            @Valid @RequestBody LoginRequest request
    ) {
        LoginResponse response = authService.login(request);
        return ApiResponse.success(SuccessStatus.LOGIN_SUCCESS, response);
    }

    @PostMapping("/signup")
    @Operation(summary = "회원가입", description = "일반 회원 가입")
    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "201", description = "회원가입 성공", content = @Content())
    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "이미 존재하는 이메일", content = @Content())
    public ResponseEntity<ApiResponse<Void>> signup(
            @RequestBody @Valid SignupRequest request) {
        authService.signup(request);
        return ApiResponse.success(SuccessStatus.SIGNUP_SUCCESS);
    }

    @PostMapping("/logout")
    @Operation(summary = "로그아웃", description = "세션 로그아웃")
    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "로그아웃 성공", content = @Content())
    public ResponseEntity<ApiResponse<Void>> logout(
            HttpServletRequest request
    ) {
        request.getSession(false).invalidate();
        return ApiResponse.success(SuccessStatus.LOGOUT_SUCCESS);
    }

    @PostMapping("/token/reissue")
    @Operation(summary = "토큰 재발급")
    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Access Token 재발급 성공", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ReissueAccessTokenResponse.class)))
    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "401", description = "Refresh Token이 유효하지 않거나 일치하지 않는 경우", content = @Content())
    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Refresh Token에서 파싱한 유저가 존재하지 않는 경우", content =@Content())
    public ResponseEntity<ApiResponse<ReissueAccessTokenResponse>> reissueAccessToken(
            @RequestHeader("Authorization") String refreshToken
    ) {
        String tokenWithoutPrefix = refreshToken.replace("Bearer ", "").trim();

        ReissueAccessTokenResponse response = authService.reissueAccessToken(tokenWithoutPrefix);
        return ApiResponse.success(SuccessStatus.CREATE_TOKEN_SUCCESS, response);
    }

}
