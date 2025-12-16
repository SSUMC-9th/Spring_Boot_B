package com.plane.umc9th.domain.member.service.command;

import com.plane.umc9th.domain.member.dto.MemberReqDTO;
import com.plane.umc9th.domain.member.dto.MemberResDTO;

public interface MemberCommandService {
    MemberResDTO.JoinDTO signup(MemberReqDTO.JoinDTO dto);
}
