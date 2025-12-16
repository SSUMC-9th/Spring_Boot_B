package com.plane.umc9th.domain.mission.dto;

import com.plane.umc9th.domain.member.validator.ExistMember;
import com.plane.umc9th.domain.review.dto.ReviewResDTO;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class MissionResDTO {
    @Builder
    public record CreateDTO(
            Long missionId
        ){}

    @Builder
    public record MissionPreViewListDTO(
            List<MissionResDTO.MissionPreViewDTO> missionList,
            Integer listSize,
            Integer totalPage,
            Long totalElements,
            Boolean isFirst,
            Boolean isLast
    ){}

    @Builder
    public record MissionPreViewDTO(
            String storeName,
            String description,
            int minAmount,
            int point,
            LocalDateTime deadline,
            LocalDate createdAt
    ){}
}
