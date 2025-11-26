package com.ssu.umc9th2.spring_boot_b.domain.restaurant.service;

import com.ssu.umc9th2.spring_boot_b.common.exception.GeneralException;
import com.ssu.umc9th2.spring_boot_b.common.status.ErrorStatus;
import com.ssu.umc9th2.spring_boot_b.domain.location.entity.Location;
import com.ssu.umc9th2.spring_boot_b.domain.restaurant.dto.request.CreateRestaurantRequestWithLocation;
import com.ssu.umc9th2.spring_boot_b.domain.restaurant.dto.response.GetRestaurantListResponse;
import com.ssu.umc9th2.spring_boot_b.domain.restaurant.dto.response.GetRestaurantMissionResponse;
import com.ssu.umc9th2.spring_boot_b.domain.restaurant.entity.Restaurant;
import com.ssu.umc9th2.spring_boot_b.domain.restaurant.repository.RestaurantCustomRepository;
import com.ssu.umc9th2.spring_boot_b.domain.restaurant.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RestaurantService {
    private final RestaurantRepository restaurantRepository;
    private final RestaurantCustomRepository restaurantCustomRepository;

    public Restaurant getRestaurantByRestaurantId(Long restaurantId){
        return restaurantRepository.findById(restaurantId).orElseThrow(()-> new GeneralException(ErrorStatus.RESTAURANT_NOT_FOUND));
    }

    public GetRestaurantListResponse getRestaurantListWithFilter(String region, String name, String orderCondition, Integer size, Integer page){
        return new GetRestaurantListResponse(restaurantCustomRepository.findAllWithFilter(region, name,orderCondition,size,page));
    }

    public void createRestaurant(CreateRestaurantRequestWithLocation request){
        if(isExistRestaurant(request.name(), request.location()))
            throw new GeneralException(ErrorStatus.RESTAURANT_ALREADY_EXIST);

        Restaurant restaurant = Restaurant.builder()
                .name(request.name())
                .category(request.category())
                .openTime(request.openTime())
                .closeTime(request.closeTime())
                .location(request.location())
                .build();

        restaurantRepository.save(restaurant);
    }

    public Page<GetRestaurantMissionResponse> getRestaurantMissionList(Long restaurantId, Pageable pageable){
        getRestaurantById(restaurantId);
        return restaurantCustomRepository.findRestaurantMissionList(restaurantId,pageable);
    }

    private boolean isExistRestaurant(String name, Location location){
        return restaurantRepository.existsByNameAndLocation(name, location);
    }

    public Restaurant getRestaurantById(Long restaurantId){
        return restaurantRepository.findById(restaurantId)
                .orElseThrow(()-> new GeneralException(ErrorStatus.RESTAURANT_NOT_FOUND));
    }
}
