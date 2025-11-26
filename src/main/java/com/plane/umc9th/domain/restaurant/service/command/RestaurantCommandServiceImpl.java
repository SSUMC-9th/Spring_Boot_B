package com.plane.umc9th.domain.restaurant.service.command;

import com.plane.umc9th.domain.catergory.entity.FoodCatergory;
import com.plane.umc9th.domain.catergory.repository.CategoryRepository;
import com.plane.umc9th.domain.member.converter.MemberConverter;
import com.plane.umc9th.domain.member.entity.Member;
import com.plane.umc9th.domain.restaurant.converter.RestaurantConverter;
import com.plane.umc9th.domain.restaurant.dto.RestaurantReqDTO;
import com.plane.umc9th.domain.restaurant.dto.RestaurantResDTO;
import com.plane.umc9th.domain.restaurant.entity.Restaurant;
import com.plane.umc9th.domain.restaurant.repository.RestaurantRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RestaurantCommandServiceImpl implements RestaurantCommandService {
    private final RestaurantRepository  restaurantRepository;
    private final CategoryRepository foodCategoryRepository;

    @Override
    @Transactional
    public RestaurantResDTO.CreateDTO createRestaurant(RestaurantReqDTO.CreateDTO dto) {
        FoodCatergory category = foodCategoryRepository.findById(dto.foodCatergoryId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 카테고리입니다."));

        Restaurant restaurant = RestaurantConverter.toRestaurant(dto);
        restaurant.setFoodCatergory(category);
        restaurantRepository.save(restaurant);

        return RestaurantConverter.toCreateDTO(restaurant);
    }
}
