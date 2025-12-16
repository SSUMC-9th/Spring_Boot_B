package com.umc9th.domain.user.service;


import com.umc9th.domain.user.converter.UserConverter;
import com.umc9th.domain.user.dto.UserReqDTO;
import com.umc9th.domain.user.dto.UserResDTO;
import com.umc9th.domain.user.entity.User;
import com.umc9th.domain.user.repository.UserRepository;
import com.umc9th.global.enums.Role;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserCommandService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public User joinUser (UserReqDTO.JoinDTO request){

        // 1. 비밀번호 암호화 (Service에서 처리)
        String encodedPassword = passwordEncoder.encode(request.password());

        // 2. DTO와 암호화된 비밀번호를 넘겨서 Entity 생성
        User newUser = UserConverter.toUser(request, encodedPassword, Role.ROLE_USER);

        // 3. 저장
        return userRepository.save(newUser);
    }
}
