package com.plane.umc9th.domain.mission.service.query;

import com.plane.umc9th.domain.mission.repository.MissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MissionQueryService {
    private final MissionRepository missionRepository;

}
