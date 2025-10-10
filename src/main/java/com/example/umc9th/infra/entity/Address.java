package com.example.umc9th.infra.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

@Embeddable
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Address {
    @Column(length = 20)
    private String city;
    @Column(length = 20)
    private String district;
    @Column(length = 40)
    private String street;
    @Column(name = "detail",length = 40)
    private String detail;
}
