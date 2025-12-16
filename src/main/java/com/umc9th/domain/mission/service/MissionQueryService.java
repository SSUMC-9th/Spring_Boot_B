package com.example.umc9th.domain.mission.service;

import com.example.umc9th.domain.mission.converter.MissionConverter;
import com.example.umc9th.domain.mission.dto.res.StoreMissionDto;
import com.example.umc9th.domain.mission.entity.Mission;
import com.example.umc9th.domain.mission.repository.MissionRepository;
import com.example.umc9th.global.apiPayload.code.GeneralErrorCode;
import com.example.umc9th.global.apiPayload.exception.GeneralException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)  // 조회 전용이니까 readOnly
public class MissionQueryService {

    private final MissionRepository missionRepository;

    // 특정 가게의 미션 목록 조회
    public Page<StoreMissionDto> getStoreMissions(Long storeId, Pageable pageable) {

        Page<Mission> missionPage = missionRepository.findByStoreId(storeId, pageable);

        return missionPage.map(MissionConverter::toStoreMissionDto);
    }
}
