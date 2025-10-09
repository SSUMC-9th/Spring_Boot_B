package com.plane.umc9th.global.entity;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public class ImageEntity extends  BaseEntity{
    @Column
    private String imageUrl;
}
