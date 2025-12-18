package com.ssu.umc9th2.spring_boot_b.domain.auth.service;

import com.ssu.umc9th2.spring_boot_b.common.exception.GeneralException;
import com.ssu.umc9th2.spring_boot_b.common.jwt.JwtService;
import com.ssu.umc9th2.spring_boot_b.common.status.ErrorStatus;
import com.ssu.umc9th2.spring_boot_b.domain.auth.dto.request.LoginRequest;
import com.ssu.umc9th2.spring_boot_b.domain.auth.dto.response.LoginResponse;
import com.ssu.umc9th2.spring_boot_b.domain.auth.dto.request.SignupRequest;
import com.ssu.umc9th2.spring_boot_b.domain.auth.dto.response.ReissueAccessTokenResponse;
import com.ssu.umc9th2.spring_boot_b.domain.user.converter.UserConverter;
import com.ssu.umc9th2.spring_boot_b.domain.user.entity.User;
import com.ssu.umc9th2.spring_boot_b.domain.user.enums.Role;
import com.ssu.umc9th2.spring_boot_b.domain.user.repository.UserRepository;
import com.ssu.umc9th2.spring_boot_b.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserConverter userConverter;
    private final UserService userService;
    private final JwtService jwtService;

//    public LoginResponse login(LoginRequest request) {
//        Authentication authentication =
//                authenticationManager.authenticate(
//                        new UsernamePasswordAuthenticationToken(
//                                request.email(),
//                                request.password()
//                        )
//                );
//
//        SecurityContextHolder.getContext()
//                .setAuthentication(authentication);
//
//        CustomUserDetails user =
//                (CustomUserDetails) authentication.getPrincipal();
//
//        return userConverter.toLoginResponse(user);
//    }

    // 로그인
    @Transactional
    public LoginResponse login(LoginRequest request) {
        User user = userService.getUserByEmail(request.email());
        validatePasswordMatch(request.password(), user.getPassword());

        String accessToken = jwtService.generateAccessToken(user);
        String refreshToken = jwtService.generateRefreshToken(user);

        userService.updateRefreshToken(user, refreshToken);
        return LoginResponse.from(accessToken, refreshToken, user);
    }

    public void signup(SignupRequest request) {

        if (userRepository.existsByEmail(request.email())) {
            throw new IllegalArgumentException("이미 존재하는 이메일");
        }

        User user = User.builder()
                .email(request.email())
                .password(passwordEncoder.encode(request.password()))
                .gender(request.gender())
                .nickname(request.nickname())
                .role(Role.ROLE_USER)
                .isDeleted(false)
        .build();

        userRepository.save(user);
    }

    // 비밀번호 매칭 검사
    private void validatePasswordMatch(String rawPassword, String encodedPassword) {
        boolean isMatch = passwordEncoder.matches(rawPassword, encodedPassword);
        if (!isMatch) throw new GeneralException(ErrorStatus.INVALID_PASSWORD);
    }

    // 토큰 재발급
    @Transactional
    public ReissueAccessTokenResponse reissueAccessToken(String refreshToken) {
        var claims = jwtService.validateRefreshToken(refreshToken);
        User user = userService.getUserByRefreshToken(claims, refreshToken);

        String newAccessToken = jwtService.generateAccessToken(user);
        String newRefreshToken = jwtService.generateRefreshToken(user);

        userService.updateRefreshToken(user, newRefreshToken);

        return new ReissueAccessTokenResponse(newAccessToken, newRefreshToken);
    }

}
