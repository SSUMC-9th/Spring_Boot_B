package com.plane.umc9th.domain.restaurant.controller;

import com.plane.umc9th.domain.member.dto.MemberReqDTO;
import com.plane.umc9th.domain.member.dto.MemberResDTO;
import com.plane.umc9th.domain.member.exception.code.MemberSuccessCode;
import com.plane.umc9th.domain.member.service.command.MemberCommandService;
import com.plane.umc9th.domain.restaurant.dto.RestaurantReqDTO;
import com.plane.umc9th.domain.restaurant.dto.RestaurantResDTO;
import com.plane.umc9th.domain.restaurant.service.command.RestaurantCommandService;
import com.plane.umc9th.global.apiPayload.ApiResponse;
import com.plane.umc9th.global.apiPayload.code.GeneralSuccessCode;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping(value = "restaurants")
@RequiredArgsConstructor
public class RestaurantController {
    private final RestaurantCommandService  restaurantCommandService;

    @PostMapping("/")
    public ApiResponse<RestaurantResDTO.CreateDTO> create(
            @RequestBody @Valid RestaurantReqDTO.CreateDTO dto
    ){
        return ApiResponse.onSuccess(GeneralSuccessCode.CREATED, restaurantCommandService.createRestaurant(dto));
    }
}
