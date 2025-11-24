package com.example.umc9th.app.domain.member.dto;

import com.example.umc9th.app.domain.mission.enums.MemberMissionStatus;
import lombok.Builder;


public class PostCreateMemberMissionResponse {
    @Builder
    public record DTO(
            Long memberMissionId
    ) {
    }
}