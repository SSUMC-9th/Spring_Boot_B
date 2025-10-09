package com.umc9th.domain.category.entity;

import com.umc9th.domain.category.enums.FoodName;
import com.umc9th.domain.user.entity.UserCategory;
import com.umc9th.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Table(name = "category")
public class Category extends BaseEntity {

    // Category(1) : UserCategory(N) 관계
    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    private List<UserCategory> userCategoryList = new ArrayList<>();

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="foodname",nullable = false)
    private FoodName foodName;
}
