package com.example.umc9th.domain.user.converter;

import com.example.umc9th.domain.user.dto.UserReqDTO;
import com.example.umc9th.domain.user.dto.UserResDTO;
import com.example.umc9th.domain.user.entity.User;
import com.example.umc9th.global.enums.Role;
import com.example.umc9th.domain.user.enums.SocialType;
public class UserConverter {

    //DTO, Salted Passwd, Role -> Entity
    public static User toUser(
            UserReqDTO.JoinDTO dto,
            String password,
            Role role
    ){
        return User.builder()
                .name(dto.name())
                .email(dto.email()) // 추가된 코드
                .password(password) // 추가된 코드
                .role(role)         // 추가된 코드
                .birthday(dto.birthday())
                .address(dto.address())
                .gender(dto.gender())
                .socialType(SocialType.naver)
                .userPoint(0L)
                .build();
    }
    //login
    public static UserResDTO.LoginDTO toLoginDTO(User user, String accessToken) {
        return UserResDTO.LoginDTO.builder()
                .userId(user.getId())
                .accessToken(accessToken)
                .build();
    }
}
