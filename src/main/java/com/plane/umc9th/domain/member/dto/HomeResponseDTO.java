package com.plane.umc9th.domain.member.dto;

import com.plane.umc9th.domain.mission.entity.Mission;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class HomeResponseDTO {
    int completeMissionCount;
    int point;
    List<Mission> missions;
}
