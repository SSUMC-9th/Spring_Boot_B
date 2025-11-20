package com.ssu.umc9th2.spring_boot_b.domain.location.repository;

import com.ssu.umc9th2.spring_boot_b.domain.location.entity.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {
}
