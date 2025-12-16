package com.ssu.umc9th2.spring_boot_b.domain.auth.service;

import com.ssu.umc9th2.spring_boot_b.domain.auth.CustomUserDetails;
import com.ssu.umc9th2.spring_boot_b.domain.auth.dto.request.LoginRequest;
import com.ssu.umc9th2.spring_boot_b.domain.auth.dto.request.LoginResponse;
import com.ssu.umc9th2.spring_boot_b.domain.auth.dto.request.SignupRequest;
import com.ssu.umc9th2.spring_boot_b.domain.user.converter.UserConverter;
import com.ssu.umc9th2.spring_boot_b.domain.user.entity.User;
import com.ssu.umc9th2.spring_boot_b.domain.user.enums.UserRole;
import com.ssu.umc9th2.spring_boot_b.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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


    public LoginResponse login(LoginRequest request) {
        Authentication authentication =
                authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(
                                request.email(),
                                request.password()
                        )
                );

        SecurityContextHolder.getContext()
                .setAuthentication(authentication);

        CustomUserDetails user =
                (CustomUserDetails) authentication.getPrincipal();

        return userConverter.toLoginResponse(user);
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
                .role(UserRole.ROLE_USER)
                .isDeleted(false)
        .build();

        userRepository.save(user);
    }
}
