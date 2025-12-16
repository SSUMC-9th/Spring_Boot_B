// com/example/umc9th/domain/user/service/UserQueryService.java (새 파일)
package com.example.umc9th.domain.user.service;

import com.example.umc9th.domain.mission.converter.UserMissionConverter;
import com.example.umc9th.domain.mission.dto.res.GetUserMissionResponse;
import com.example.umc9th.domain.mission.repository.UserMissionRepository;
import com.example.umc9th.domain.review.dto.UserReviewDto;
import com.example.umc9th.domain.user.repository.MyPageRepository;
import com.example.umc9th.global.apiPayload.code.GeneralErrorCode;
import com.example.umc9th.global.apiPayload.exception.GeneralException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserQueryService {

    private final MyPageRepository myPageRepository;
    private final UserMissionRepository userMissionRepository;

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

}