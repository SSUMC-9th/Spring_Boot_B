package com.ssu.umc9th2.spring_boot_b.domain.restaurant.repository;

import com.ssu.umc9th2.spring_boot_b.domain.location.entity.Location;
import com.ssu.umc9th2.spring_boot_b.domain.restaurant.entity.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
    Boolean existsByNameAndLocation(String name, Location location);
}
