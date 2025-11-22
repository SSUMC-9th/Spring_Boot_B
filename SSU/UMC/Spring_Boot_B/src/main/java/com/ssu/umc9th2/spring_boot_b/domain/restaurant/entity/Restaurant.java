package com.ssu.umc9th2.spring_boot_b.domain.restaurant.entity;

import com.ssu.umc9th2.spring_boot_b.common.base.BaseEntity;
import com.ssu.umc9th2.spring_boot_b.domain.location.entity.Location;
import com.ssu.umc9th2.spring_boot_b.domain.mission.entity.Mission;
import jakarta.persistence.*;
import lombok.*;
import java.time.*;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Restaurant extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String category;

    private LocalTime openTime;

    private LocalTime closeTime;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "location_id")
    private Location location;

    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL)
    private List<Mission> missions;
}

