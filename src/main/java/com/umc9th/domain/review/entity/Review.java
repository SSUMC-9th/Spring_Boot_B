package com.umc9th.domain.review.entity;

import com.umc9th.domain.review.entity.ReviewFeedback;
import com.umc9th.domain.review.entity.ReviewPhoto;
import com.umc9th.domain.store.entity.Store;
import com.umc9th.domain.user.entity.User;
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
@Table(name = "review")
public class Review extends BaseEntity {

    // Review(1) : ReviewPhoto(N) 관계
    @OneToMany(mappedBy = "review", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ReviewPhoto> reviewPhotoList = new ArrayList<>();

    // Review(1) : ReviewFeedback(1) 관계
    @OneToOne(mappedBy = "review", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private ReviewFeedback feedback;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "content",nullable = false)
    private String content;

    @Column(name = "star",nullable = false)
    private int star;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id")
    private Store store;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
}