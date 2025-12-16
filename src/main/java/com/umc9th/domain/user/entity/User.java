package com.example.umc9th.domain.user.entity;

import com.example.umc9th.global.enums.Role;
import com.example.umc9th.domain.user.enums.Address;
import com.example.umc9th.domain.user.enums.Gender;
import com.example.umc9th.domain.user.enums.SocialType;
import com.example.umc9th.global.entity.BaseEntity;
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
@Table(name = "user")
public class User extends BaseEntity {

    // UserCategory와의 1:N 관계 설정
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<UserCategory> userCategoryList = new ArrayList<>();

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name ="name", length =10, nullable = false)
    private String name;

    @Column(name = "gender",nullable = false)
    private Gender gender;

    @Column(name ="birthday", nullable = false)
    private LocalDate birthday;

    @Column(name = "address", nullable = false)
    private Address address;

    @Column(name = "socialtype",nullable = false)
    private SocialType socialType;

    @Column(name ="userpoint", nullable = false)
    private Long userPoint;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;


}
