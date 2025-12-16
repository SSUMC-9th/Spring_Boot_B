package com.plane.umc9th.domain.member.service.query;

import com.plane.umc9th.domain.member.dto.MemberReqDTO;
import com.plane.umc9th.domain.member.dto.MemberResDTO;
import jakarta.validation.Valid;

public interface MemberQueryService {
    MemberResDTO.LoginDTO login(MemberReqDTO.@Valid LoginDTO dto);
}
