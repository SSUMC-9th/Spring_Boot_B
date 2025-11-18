package com.example.umc9th.app.domain.member.entity;


import com.example.umc9th.app.domain.member.enums.AuthProvider;
import com.example.umc9th.app.domain.member.enums.Gender;
import com.example.umc9th.app.domain.review.entity.Review;
import com.example.umc9th.infra.entity.Address;
import com.example.umc9th.infra.entity.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Table(name = "member")
public class Member extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 10, nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @Builder.Default
    private Gender gender = Gender.NONE;

    @Builder.Default
    @Enumerated(EnumType.STRING)
    @Column(name = "auth_provider", nullable = false, length = 10)
    private AuthProvider authProvider = AuthProvider.LOCAL;

    @Column(nullable = false)
    private LocalDate birth;

    @Column(length = 30, nullable = false)
    private String email;

    @Column(name = "phone_num", length = 20, unique = true)
    private String phoneNum;

    @Builder.Default
    @Column(nullable = false)
    private Long point = 0L;

    @Builder.Default
    @Column(name = "verified", nullable = false)
    private boolean isPhoneNumVerified = false;

    @Builder.Default
    @Column(name = "active", nullable = false)
    private boolean isActive = true;

    @Embedded
    private Address address;

    @OneToMany(mappedBy = "member")
    @Builder.Default
    private List<MemberFoodCategory> memberFoodCategories = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<Review> reviews = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<Alarm> alarms = new ArrayList<>();

//    @OneToMany(mappedBy = "member", orphanRemoval = true)
//    @Builder.Default
//    private List<MemberMission> memberMissions = new ArrayList<>();

    @Column(name = "event_enabled")
    private boolean eventEnabled;

    @Column(name = "reply_enabled")
    private boolean replyEnabled;

    @Column(name = "qna_enabled")
    private boolean qnaEnabled;
}
