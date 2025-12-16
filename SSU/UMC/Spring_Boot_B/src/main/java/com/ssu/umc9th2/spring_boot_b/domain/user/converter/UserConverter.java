package com.ssu.umc9th2.spring_boot_b.domain.user.converter;

import com.ssu.umc9th2.spring_boot_b.domain.auth.CustomUserDetails;
import com.ssu.umc9th2.spring_boot_b.domain.auth.dto.request.LoginResponse;
import com.ssu.umc9th2.spring_boot_b.domain.user.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserConverter {

    public LoginResponse toLoginResponse(User user) {
        return new LoginResponse(
                user.getId(),
                user.getEmail(),
                user.getNickname()
        );
    }

    public LoginResponse toLoginResponse(CustomUserDetails userDetails) {
        return new LoginResponse(
                userDetails.getUserId(),
                userDetails.getEmail(),
                userDetails.getNickname()
        );
    }
}
