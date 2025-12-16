// com/example/umc9th/domain/user/service/UserQueryService.java (새 파일)
package com.umc9th.domain.user.service;

import com.umc9th.domain.mission.converter.UserMissionConverter;
import com.umc9th.domain.mission.dto.res.GetUserMissionResponse;
import com.umc9th.domain.mission.repository.UserMissionRepository;
import com.umc9th.domain.review.dto.UserReviewDto;
import com.umc9th.domain.user.converter.UserConverter;
import com.umc9th.domain.user.dto.UserReqDTO;
import com.umc9th.domain.user.dto.UserResDTO;
import com.umc9th.domain.user.entity.User;
import com.umc9th.domain.user.repository.MyPageRepository;
import com.umc9th.domain.user.repository.UserRepository;
import com.umc9th.domain.user.userdetail.CustomUserDetails;
import com.umc9th.global.apiPayload.code.GeneralErrorCode;
import com.umc9th.global.apiPayload.exception.GeneralException;
import com.umc9th.global.jwt.JwtUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserQueryService {

    private final MyPageRepository myPageRepository;
    private final UserMissionRepository userMissionRepository;
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder encoder;


    public Page<UserReviewDto> getUserReviewList(Long userId, Pageable pageable) {

        // 유저 존재 여부 검증
        if (!myPageRepository.existsById(userId)) {
            throw new GeneralException(GeneralErrorCode.NOT_FOUND);
        }

        // Repository의 페이징 쿼리 메서드 호출
        return myPageRepository.findAllUserReviewsByUserId(userId, pageable);
    }


    public Page<GetUserMissionResponse> getUserMissionList(
            Long userId,
            Boolean isCompleted,
            Pageable pageable
    ) {
        return userMissionRepository
                .findByUser_IdAndStatus(userId, isCompleted, pageable)
                .map(UserMissionConverter::toUserMissionResponse);
    }



    public UserResDTO.LoginDTO login(
            UserReqDTO.@Valid LoginDTO dto
    ) {

        // Member 조회
        User user = userRepository.findByEmail(dto.email())
                .orElseThrow(() -> new GeneralException(GeneralErrorCode.NOT_FOUND));

        // 비밀번호 검증
        if (!encoder.matches(dto.password(), user.getPassword())){
            throw new GeneralException(GeneralErrorCode.NOT_MATCH);
        }

        // JWT 토큰 발급용 UserDetails
        CustomUserDetails userDetails = new CustomUserDetails(user);

        // 엑세스 토큰 발급
        String accessToken = jwtUtil.createAccessToken(userDetails);

        // DTO 조립
        return UserConverter.toLoginDTO(user, accessToken);
    }
}