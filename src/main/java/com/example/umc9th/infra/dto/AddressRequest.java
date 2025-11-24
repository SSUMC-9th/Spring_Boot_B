package com.example.umc9th.infra.dto;

public record AddressRequest(
        String city,
        String district,
        String street,
        String detail
) {}
