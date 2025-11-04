package com.ssu.umc9th2.spring_boot_b.domain.restaurant.service;

import com.ssu.umc9th2.spring_boot_b.domain.restaurant.dto.response.GetRestaurantListResponse;
import com.ssu.umc9th2.spring_boot_b.domain.restaurant.entity.Restaurant;
import com.ssu.umc9th2.spring_boot_b.domain.restaurant.repository.RestaurantCustomRepositoryImpl;
import com.ssu.umc9th2.spring_boot_b.domain.restaurant.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RestaurantService {
    private final RestaurantRepository restaurantRepository;
    private final RestaurantCustomRepositoryImpl restaurantCustomRepositoryImpl;

    public Restaurant getRestaurantFindByRestaurantId(Long restaurantId){
        return restaurantRepository.findById(restaurantId).orElse(null);
    }

    public GetRestaurantListResponse getRestaurantListWithFilter(String region, String name, String orderCondition, Integer size, Integer page){
        return new GetRestaurantListResponse(restaurantCustomRepositoryImpl.findAllWithFilter(region, name,orderCondition,size,page));
    }
}
