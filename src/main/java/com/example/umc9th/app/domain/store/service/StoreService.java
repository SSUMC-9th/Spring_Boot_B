package com.example.umc9th.app.domain.store.service;

import com.example.umc9th.app.domain.mission.entity.Mission;
import com.example.umc9th.app.domain.mission.repository.MissionRepository;
import com.example.umc9th.app.domain.store.dto.PostCreateStoreMissionRequest;
import com.example.umc9th.app.domain.store.dto.PostCreateStoreMissionResponse;
import com.example.umc9th.app.domain.store.dto.PostCreateStoreRequest;
import com.example.umc9th.app.domain.store.dto.PostCreateStoreResponse;
import com.example.umc9th.app.domain.store.entity.Store;
import com.example.umc9th.app.domain.store.entity.StoreHours;
import com.example.umc9th.app.domain.store.exception.StoreErrorCode;
import com.example.umc9th.app.domain.store.exception.StoreException;
import com.example.umc9th.app.domain.store.repository.StoreRepository;
import com.example.umc9th.infra.entity.Address;
import com.example.umc9th.infra.entity.FoodCategory;
import com.example.umc9th.infra.enums.FoodCategoryName;
import com.example.umc9th.infra.exception.FoodErrorCode;
import com.example.umc9th.infra.exception.FoodException;
import com.example.umc9th.infra.repository.FoodCategoryRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StoreService {
    private final StoreRepository storeRepository;
    private final MissionRepository missionRepository;
    private final FoodCategoryRepository foodCategoryRepository;

    public Store findStoreById(Long storeId) {
        return storeRepository.findById(storeId).orElse(null);
    }
    @Transactional
    public PostCreateStoreResponse.CreateStoreDTO createStore(PostCreateStoreRequest.CreateStoreDTO dto) {
        FoodCategory foodCategory = foodCategoryRepository
                .findByName(dto.foodCategoryName())
                .orElseThrow(() -> new FoodException(FoodErrorCode.NOT_FOUND));

        Address address = new Address(
                dto.addressRequest().city(),
                dto.addressRequest().district(),
                dto.addressRequest().street(),
                dto.addressRequest().detail()
        );
        Store newStore = Store.builder()
                .name(dto.name())
                .backgroundImg(dto.backgroundImg())
                .foodCategory(foodCategory)
                .address(address)
                .build();
        List<StoreHours> storeHoursList = dto.storeHoursList().stream()
                .map(h -> StoreHours.builder()
                        .week(h.week())
                        .open(h.open())
                        .close(h.close())
                        .isOvernight(h.overnight())
                        .isClosed(h.closed())
                        .store(newStore)
                        .build()
                )
                .toList();
        newStore.getStoreHoursList().addAll(storeHoursList);
        Store saved = storeRepository.save(newStore);

        return new PostCreateStoreResponse.CreateStoreDTO(
                saved.getId(),
                saved.getCreatedAt()
        );
    }
    @Transactional
    public PostCreateStoreMissionResponse.CreateStoreMissionDTO createStoreMission(PostCreateStoreMissionRequest.CreateStoreMissionDTO dto) {
        Store store = storeRepository.findById(dto.storeId())
                .orElseThrow(() -> new StoreException(StoreErrorCode.STORE_NOT_FOUND));

        Mission newStoreMission = Mission.builder()
                .store(store)
                .reward(dto.reward())
                .cashRequirement(dto.cashRequirement())
                .dueDate(dto.dueDate())
                .build();
     Mission saved = missionRepository.save(newStoreMission);

        return new PostCreateStoreMissionResponse.CreateStoreMissionDTO(
                saved.getId(),
                saved.getCreatedAt()
        );
    }
}
