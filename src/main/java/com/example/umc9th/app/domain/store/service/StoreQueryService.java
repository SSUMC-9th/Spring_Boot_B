package com.example.umc9th.app.domain.store.service;

import com.example.umc9th.app.domain.mission.entity.Mission;
import com.example.umc9th.app.domain.mission.repository.MissionRepository;
import com.example.umc9th.app.domain.store.dto.GetStoreMissionResponse;
import com.example.umc9th.app.domain.store.entity.Store;
import com.example.umc9th.app.domain.store.exception.StoreErrorCode;
import com.example.umc9th.app.domain.store.exception.StoreException;
import com.example.umc9th.app.domain.store.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class StoreQueryService
{
    private final StoreRepository storeRepository;
    private final MissionRepository missionRepository;
    @Transactional(readOnly = true)
    public Page<GetStoreMissionResponse> getStoreMission(Long storeId, PageRequest pageRequest) {
       Store store = storeRepository.findById(storeId).orElseThrow(()-> new StoreException(StoreErrorCode.STORE_NOT_FOUND));

        Page<Mission> missions = missionRepository.findAllByStore(store, pageRequest);
        return missions.map(m -> new GetStoreMissionResponse(
                m.getId(),
                m.getStore().getId(),
                m.getReward(),
                m.getCashRequirement(),
                m.getDueDate()
        ));
    }
}
