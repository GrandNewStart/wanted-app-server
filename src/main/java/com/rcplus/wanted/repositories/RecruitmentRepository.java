package com.rcplus.wanted.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rcplus.wanted.models.Recruitment;

public interface RecruitmentRepository extends JpaRepository<Recruitment,Long>{
    Optional<Recruitment> findById(Long recruitmentId);

    
}
