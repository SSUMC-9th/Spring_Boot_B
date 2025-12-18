package com.ssu.umc9th2.spring_boot_b.domain.user.repository;

import com.ssu.umc9th2.spring_boot_b.domain.user.entity.User;
import com.ssu.umc9th2.spring_boot_b.domain.user.enums.LoginType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Boolean existsByEmail(String email);
    Optional<User>findByEmailAndIsDeletedFalse(String email);
    Optional<User> findByEmail(String email);
    Optional<User> findByLoginTypeAndProviderId(LoginType loginType, String providerId);
}
