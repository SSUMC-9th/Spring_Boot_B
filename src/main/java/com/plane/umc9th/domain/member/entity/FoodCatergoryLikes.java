package com.plane.umc9th.domain.member.entity;

import com.plane.umc9th.domain.catergory.entity.FoodCatergory;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@NoArgsConstructor(access= AccessLevel.PROTECTED)
@AllArgsConstructor(access=AccessLevel.PRIVATE)
@Getter
@Setter
@Table(name="food_category_likes")
public class FoodCatergoryLikes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // ✅ User와 N:1
    @ManyToOne
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    // ✅ Category와 N:1
    @ManyToOne
    @JoinColumn(name = "food_category_id", nullable = false)
    private FoodCatergory foodCatergory;
}
