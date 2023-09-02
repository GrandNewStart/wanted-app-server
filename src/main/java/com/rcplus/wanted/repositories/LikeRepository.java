package com.rcplus.wanted.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rcplus.wanted.models.Like;

public interface LikeRepository extends JpaRepository<Like, Long>{
    int countByRecruitmentId(Long recruitmentId);
    Optional<Like> findByUserIdAndRecruitmentId(Long userId, Long recruitmentId);
}

