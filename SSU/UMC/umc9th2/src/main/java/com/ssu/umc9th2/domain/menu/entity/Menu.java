package com.ssu.umc9th2.domain.menu.entity;

import com.ssu.umc9th2.common.base.BaseEntity;
import com.ssu.umc9th2.domain.restaurant.entity.Restaurant;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Menu extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;

    private String name;

    private String description;

    private Long price;

}

