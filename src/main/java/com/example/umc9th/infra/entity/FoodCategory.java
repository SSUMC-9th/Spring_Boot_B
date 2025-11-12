package com.example.umc9th.infra.entity;

import com.example.umc9th.app.domain.member.entity.MemberFoodCategory;
import com.example.umc9th.app.domain.store.entity.Store;
import com.example.umc9th.infra.enums.FoodCategoryName;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Table(name = "food_category")
public class FoodCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 60)
    @Enumerated(EnumType.STRING)
    private FoodCategoryName name;

    @OneToMany(mappedBy = "foodCategory", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Store> stores = new ArrayList<>();

    @OneToMany(mappedBy = "foodCategory", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<MemberFoodCategory> memberFoodCategoryList = new ArrayList<>();
}
