package com.plane.umc9th.domain.restaurant.converter;

import com.plane.umc9th.domain.member.dto.MemberReqDTO;
import com.plane.umc9th.domain.member.dto.MemberResDTO;
import com.plane.umc9th.domain.member.entity.Member;
import com.plane.umc9th.domain.restaurant.dto.RestaurantReqDTO;
import com.plane.umc9th.domain.restaurant.dto.RestaurantResDTO;
import com.plane.umc9th.domain.restaurant.entity.Restaurant;

public class RestaurantConverter {

    // Entity -> DTO
    public static RestaurantResDTO.CreateDTO toCreateDTO(
            Restaurant restaurant
    ){
        return RestaurantResDTO.CreateDTO.builder()
                .restaurantId(restaurant.getId())
                .createAt(restaurant.getCreatedAt())
                .build();
    }

    // DTO -> Entity
    public static Restaurant toRestaurant(
            RestaurantReqDTO.CreateDTO dto
    ){
        return Restaurant.builder()
                .name(dto.name())
                .description(dto.description())
                .address(dto.address())
                .openingHours(dto.openingHours())
                .build();
    }
}