package com.plane.umc9th.domain.review.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.plane.umc9th.domain.member.entity.Member;
import com.plane.umc9th.domain.restaurant.entity.Restaurant;
import com.plane.umc9th.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor(access=AccessLevel.PRIVATE)
@Table(name="review")
@Getter
@Setter
public class Review extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    @JsonIgnore // 반복 방지
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;

    @OneToMany(mappedBy = "review", fetch = FetchType.LAZY)
    private List<ReviewImage> images;

    private float rating;
    private String content;
    private String reply;
}
