package com.ssu.umc9th2.spring_boot_b.domain.user.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum LoginType {
    EMAIL("EMAIL"),
    GOOGLE("GOOGLE"),
    KAKAO("KAKAO");

    private final String type;
}
