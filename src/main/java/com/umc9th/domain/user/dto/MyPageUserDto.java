package com.umc9th.domain.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

public class MyPageUserDto {
    // User.name
    private String username;

    // User.addres
    private Address address;

    // User.userPoint
    private Long userPoint;

    // COUNT(r.review_id)
    private Long reviewCount;
}
