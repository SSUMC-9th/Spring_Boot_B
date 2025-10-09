package com.ssu.umc9th2.spring_boot_b.domain.inquiry.entity;

import com.ssu.umc9th2.common.base.BaseEntity;
import com.ssu.umc9th2.spring_boot_b.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Inquiry extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    private String title;

    private String content;

    private String imageUrl;

    @Column(nullable = false)
    private Boolean isAnswered;
}

