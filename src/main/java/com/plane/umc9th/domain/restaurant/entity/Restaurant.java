package com.plane.umc9th.domain.restaurant.entity;

import com.plane.umc9th.domain.catergory.entity.FoodCatergory;
import com.plane.umc9th.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@NoArgsConstructor(access= AccessLevel.PROTECTED)
@AllArgsConstructor(access=AccessLevel.PRIVATE)
@Getter
@Setter
@Table(name="restaurant")
public class Restaurant extends BaseEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "food_category_id")
    private FoodCatergory foodCatergory;

    @Column
    private String name;

    @Column
    private String description;

    @Column
    private String address;

    @Column
    private String openingHours;
}
