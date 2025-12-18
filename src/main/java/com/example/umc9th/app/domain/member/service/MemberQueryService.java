package com.example.umc9th.app.domain.member.service;

import com.example.umc9th.app.domain.member.dto.PostMemberLoginRequest;
import com.example.umc9th.app.domain.member.dto.PostMemberLoginResponse;
import com.example.umc9th.app.domain.member.entity.Member;
import com.example.umc9th.app.domain.member.exception.MemberException;
import com.example.umc9th.app.domain.member.exception.code.MemberErrorCode;
import com.example.umc9th.app.domain.member.repository.MemberRepository;
import com.example.umc9th.infra.security.CustomUserDetails;
import com.example.umc9th.infra.security.jwt.JwtUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberQueryService {
//세션 로그인
//    public PostMemberLoginRequest.LoginDTO login(PostMemberLoginRequest.LoginDTO dto) {
//    return null;
//    }
    private final MemberRepository memberRepository;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder encoder;

    public PostMemberLoginResponse.LoginDTO login(
            PostMemberLoginRequest.@Valid LoginDTO dto
    ) {
        // Member 조회
        Member member = memberRepository.findByEmail(dto.email())
                .orElseThrow(() -> new MemberException(MemberErrorCode.NOT_FOUND));

        // 비밀번호 검증
        if (!encoder.matches(dto.password(), member.getPassword())){
            throw new MemberException(MemberErrorCode.INVALID);
        }

        // JWT 토큰 발급용 UserDetails
        CustomUserDetails userDetails = new CustomUserDetails(member);

        // 엑세스 토큰 발급
        String accessToken = jwtUtil.createAccessToken(userDetails);
        return new PostMemberLoginResponse.LoginDTO(
                member.getId(),
                accessToken
        );
    }
}