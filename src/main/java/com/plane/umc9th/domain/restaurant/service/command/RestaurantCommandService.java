package com.plane.umc9th.domain.restaurant.service.command;

import com.plane.umc9th.domain.restaurant.dto.RestaurantReqDTO;
import com.plane.umc9th.domain.restaurant.dto.RestaurantResDTO;

public interface RestaurantCommandService {
    public RestaurantResDTO.CreateDTO createRestaurant(RestaurantReqDTO.CreateDTO dto);
}
