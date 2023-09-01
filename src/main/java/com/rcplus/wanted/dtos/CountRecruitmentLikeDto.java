package com.rcplus.wanted.dtos;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

public class CountRecruitmentLikeDto {

    @Getter
    @Setter
    @Builder
    public static class Response {
        private int likeCount;
    }

}


