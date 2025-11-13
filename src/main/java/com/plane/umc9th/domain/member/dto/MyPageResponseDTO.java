package com.plane.umc9th.domain.member.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MyPageResponseDTO {
    String name;
    String email;
    Boolean isVerified;
    int point;
}
