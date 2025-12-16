package com.ssu.umc9th2.spring_boot_b.domain.user.entity;

import com.ssu.umc9th2.spring_boot_b.common.base.BaseEntity;
import com.ssu.umc9th2.spring_boot_b.domain.user.enums.UserRole;
import jakarta.persistence.*;
import lombok.*;
import java.time.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String profileLink;

    @Column(nullable = false)
    private String gender;

    private LocalDate birth;

    private String address;

    private Long point;

    private String phone;

    private String nickname;

    private String kakaoId;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private Boolean isDeleted;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private UserRole role;

    private String refreshToken;

    public void updateRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }
}

