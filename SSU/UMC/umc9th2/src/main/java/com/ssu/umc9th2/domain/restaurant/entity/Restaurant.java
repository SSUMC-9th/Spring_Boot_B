package com.ssu.umc9th2.domain.restaurant.entity;

import com.ssu.umc9th2.common.base.BaseEntity;
import com.ssu.umc9th2.domain.location.entity.Location;
import jakarta.persistence.*;
import lombok.*;
import java.time.*;

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "location_id")
    private Location location;
}

