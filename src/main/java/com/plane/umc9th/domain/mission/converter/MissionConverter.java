package com.plane.umc9th.domain.mission.converter;

import com.plane.umc9th.domain.mission.dto.MissionResDTO;
import com.plane.umc9th.domain.mission.entity.Mission;
import com.plane.umc9th.domain.review.converter.ReviewConverter;
import com.plane.umc9th.domain.review.dto.ReviewReqDTO;
import com.plane.umc9th.domain.review.dto.ReviewResDTO;
import com.plane.umc9th.domain.review.entity.Review;
import org.springframework.data.domain.Page;

import java.time.LocalDate;

public class MissionConverter {
    public static MissionResDTO.MissionPreViewListDTO toMissionPreviewListDTO(
            Page<Mission> result
    ){
        return MissionResDTO.MissionPreViewListDTO.builder()
                .missionList(result.getContent().stream()
                        .map(MissionConverter::toMissionPreviewDTO)
                        .toList()
                )
                .listSize(result.getSize())
                .totalPage(result.getTotalPages())
                .totalElements(result.getTotalElements())
                .isFirst(result.isFirst())
                .isLast(result.isLast())
                .build();
    }

    public static MissionResDTO.MissionPreViewDTO toMissionPreviewDTO(
            Mission mission
    ){
        return MissionResDTO.MissionPreViewDTO.builder()
                .storeName(
                        mission.getRestaurant() != null
                                ? mission.getMember().getName()
                                : "알 수 없음")
                .description(mission.getDescription())
                .minAmount(mission.getMinAmount())
                .point(mission.getPoint())
                .deadline(mission.getDeadline())
                .createdAt(LocalDate.from(mission.getCreatedAt()))
                .build();
    }
}
