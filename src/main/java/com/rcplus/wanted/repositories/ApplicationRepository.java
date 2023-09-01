package com.rcplus.wanted.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rcplus.wanted.models.Application;

@Repository
public interface ApplicationRepository extends JpaRepository<Application, Long> {
    Optional<Application> findByUserIdAndRecruitmentId(Long userId, Long recruitmentId);
    List<Application> findByUserId(Long userId);

}
