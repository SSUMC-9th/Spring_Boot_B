package com.ssu.umc9th2.spring_boot_b.domain.auth.dto.response;

import com.ssu.umc9th2.spring_boot_b.domain.user.entity.User;
import com.ssu.umc9th2.spring_boot_b.domain.user.enums.LoginType;

public record UserInfo(
        Long userId,
        String nickname,
        String email,
        LoginType loginType
) {
    public static UserInfo from(User user){
        return new UserInfo(user.getId(), user.getNickname(), user.getEmail(), user.getLoginType());
    }
}
