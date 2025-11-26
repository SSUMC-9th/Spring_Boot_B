package com.ssu.umc9th2.spring_boot_b.domain.review.entity;

import com.ssu.umc9th2.spring_boot_b.common.base.BaseEntity;
import com.ssu.umc9th2.spring_boot_b.domain.restaurant.entity.Restaurant;
import com.ssu.umc9th2.spring_boot_b.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Review extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;

    private Double rating;

    private String content;

    @OneToMany(mappedBy = "review", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ReviewImage> reviewImageList;
}

