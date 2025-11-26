package com.example.umc9th.app.domain.member.dto;

import com.example.umc9th.infra.entity.Address;

public record GetMemberHomeResponse(
        Address address,
        Long alarmCount
//추후에 필요하면 추가
) {
}
