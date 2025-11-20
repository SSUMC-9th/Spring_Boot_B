package com.ssu.umc9th2.spring_boot_b.domain.mission.repository;

import com.ssu.umc9th2.spring_boot_b.domain.mission.entity.Mission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MissionRepository extends JpaRepository<Mission, Long> {
}
