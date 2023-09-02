package com.rcplus.wanted.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

public class DeleteRecruitmentDto {
 
    @Getter
    @Setter
    public static class Request {
        @JsonProperty("recruitment_id")
        private Long recruitmentId;
    }

}
