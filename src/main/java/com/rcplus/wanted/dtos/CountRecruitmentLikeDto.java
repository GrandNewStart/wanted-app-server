package com.rcplus.wanted.dtos;


import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

public class CountRecruitmentLikeDto {

    @Getter
    @Setter
    @Builder
    public static class Response {
        @JsonProperty("like_count")
        private int likeCount;
    }

}


