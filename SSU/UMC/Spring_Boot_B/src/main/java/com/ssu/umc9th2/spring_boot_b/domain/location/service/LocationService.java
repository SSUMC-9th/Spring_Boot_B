package com.ssu.umc9th2.spring_boot_b.domain.location.service;

import com.ssu.umc9th2.spring_boot_b.common.exception.GeneralException;
import com.ssu.umc9th2.spring_boot_b.common.status.ErrorStatus;
import com.ssu.umc9th2.spring_boot_b.domain.location.entity.Location;
import com.ssu.umc9th2.spring_boot_b.domain.location.repository.LocationRepository;
import com.ssu.umc9th2.spring_boot_b.domain.restaurant.dto.request.CreateRestaurantRequest;
import com.ssu.umc9th2.spring_boot_b.domain.restaurant.dto.request.CreateRestaurantRequestWithLocation;
import com.ssu.umc9th2.spring_boot_b.domain.restaurant.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LocationService {

    private final LocationRepository locationRepository;
    private final RestaurantService restaurantService;

    public void createRestaurantInLocation(Long restaurantId, CreateRestaurantRequest request) {
        Location location = getLocationById(restaurantId);
        restaurantService.createRestaurant(new CreateRestaurantRequestWithLocation(request.name(), request.category(), request.openTime(), request.closeTime(),location));
    }

    public Location getLocationById(Long locationId) {
        return locationRepository.findById(locationId).orElseThrow(()-> new GeneralException(ErrorStatus.LOCATION_NOT_FOUND));
    }
}
