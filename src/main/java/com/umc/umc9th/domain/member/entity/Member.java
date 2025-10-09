package com.plane.umc9th.domain.member.entity;

import com.plane.umc9th.domain.catergory.entity.FoodCatergory;
import com.plane.umc9th.domain.inquiry.entity.Inquiry;
import com.plane.umc9th.domain.member.enums.Gender;
import com.plane.umc9th.domain.member.enums.Provider;
import com.plane.umc9th.domain.member.enums.Status;
import com.plane.umc9th.domain.mission.entity.Mission;
import com.plane.umc9th.domain.notification.entity.Notification;
import com.plane.umc9th.domain.review.entity.Review;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Builder
@NoArgsConstructor(access=AccessLevel.PROTECTED)
@AllArgsConstructor(access=AccessLevel.PRIVATE)
@Getter
@Setter
@Table(name="member")
// TODO: + Restaurant Owner
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Foreign Keys
    @OneToOne
    @JoinColumn(name = "settingId")
    private MemberSetting setting;

    @OneToOne
    @JoinColumn(name = "termId")
    private Term term;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<FoodCatergoryLikes> foodCatergoryLikes;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<Mission> missions;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<Inquiry> inquiries;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<Review> reviews;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<Notification> notifications;

    // Columns
    private String name;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    @Column(columnDefinition = "DATE")
    private LocalDate birthDate;
    private String address;
    private String email;
    @Enumerated(EnumType.STRING)
    private Provider provider;
    private int point;
    private String phoneNumber;
    private boolean is_verified;
    @Column(columnDefinition = "DATETIME")
    private LocalDateTime inactive_date;
    @Enumerated(EnumType.STRING)
    private Status status;
}
