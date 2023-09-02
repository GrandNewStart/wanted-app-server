package com.rcplus.wanted.dtos;

import com.rcplus.wanted.models.Like;

import jakarta.persistence.Column;
import lombok.Builder;

@Builder
public class LikeDto {
    
    private Long id;
    @Column(name = "user_id")
    private Long userId;
    @Column(name = "recruitment_d")
    private Long recruitmentId;

    public static LikeDto from(Like like) {
        return LikeDto.builder()
            .id(like.getId())
            .userId(like.getUserId())
            .recruitmentId(like.getRecruitmentId())
            .build();
    }

}
