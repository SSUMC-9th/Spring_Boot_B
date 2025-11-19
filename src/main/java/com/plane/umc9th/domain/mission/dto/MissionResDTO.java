package com.plane.umc9th.domain.mission.dto;

import com.plane.umc9th.domain.member.validator.ExistMember;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.time.LocalDateTime;

public class MissionResDTO {
    @Builder
    public record CreateDTO(
            Long missionId
        ){}
}
