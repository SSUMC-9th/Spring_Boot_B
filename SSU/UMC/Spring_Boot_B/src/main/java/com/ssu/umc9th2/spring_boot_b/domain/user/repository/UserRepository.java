package com.ssu.umc9th2.spring_boot_b.domain.user.repository;

import com.ssu.umc9th2.spring_boot_b.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
