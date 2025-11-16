package com.ssu.umc9th2.spring_boot_b.domain.mission.dto.request;

import java.time.LocalDateTime;

public record CreateMissionRequest(
        String content,
        Long point,
        String foodName,
        LocalDateTime deadline,
        String verificationCode
) {
}
