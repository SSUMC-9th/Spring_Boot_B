package com.plane.umc9th.domain.review.entity;

import com.plane.umc9th.domain.inquiry.enums.InquiryType;
import com.plane.umc9th.domain.member.entity.Member;
import com.plane.umc9th.global.entity.BaseEntity;
import com.plane.umc9th.global.entity.ImageEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Builder
@NoArgsConstructor(access= AccessLevel.PROTECTED)
@AllArgsConstructor(access=AccessLevel.PRIVATE)
@Table(name="review")
public class Review extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "review", fetch = FetchType.LAZY)
    private List<ReviewImage> images;

    private float rating;
    private String content;
    private String reply;
}
