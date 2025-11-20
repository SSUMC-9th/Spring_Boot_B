package com.ssu.umc9th2.spring_boot_b.domain.mission.service;

import com.ssu.umc9th2.spring_boot_b.common.exception.GeneralException;
import com.ssu.umc9th2.spring_boot_b.common.status.ErrorStatus;
import com.ssu.umc9th2.spring_boot_b.domain.mission.dto.request.CreateMissionRequest;
import com.ssu.umc9th2.spring_boot_b.domain.mission.entity.Mission;
import com.ssu.umc9th2.spring_boot_b.domain.mission.repository.MissionRepository;
import com.ssu.umc9th2.spring_boot_b.domain.restaurant.entity.Restaurant;
import com.ssu.umc9th2.spring_boot_b.domain.restaurant.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class MissionService {

    private final MissionRepository missionRepository;
    private final RestaurantService restaurantService;

    public void createMission(Long restaurantId, CreateMissionRequest request){
        Restaurant restaurant = restaurantService.getRestaurantByRestaurantId(restaurantId);
        Mission mission = Mission.builder()
                .restaurant(restaurant)
                .point(request.point())
                .foodName(request.foodName())
                .deadline(request.deadline())
                .verificationCode(request.verificationCode())
                .build();

        missionRepository.save(mission);
    }

    public Mission getMissionByMissionId(Long missionId){
        return missionRepository.findById(missionId).orElseThrow(()-> new GeneralException(ErrorStatus.MISSION_NOT_FOUND));
    }
}
