package com.example.umc9th.domain.mission.converter;

import com.example.umc9th.domain.mission.dto.res.GetUserMissionResponse;
import com.example.umc9th.domain.user.entity.UserMission;

public class UserMissionConverter {

    private UserMissionConverter() {}

    public static GetUserMissionResponse toUserMissionResponse(UserMission userMission) {

        return new GetUserMissionResponse(
                userMission.getMission().getId(),              // 미션 ID
                userMission.getMission().getContent(),         // 미션 내용
                userMission.getMission().getPoint(),           // 포인트
                userMission.getMission().getStore().getStoreName(), // 가게 이름
                userMission.getStatus()                        // 완료 여부                      // isCompleted
        );
    }
}