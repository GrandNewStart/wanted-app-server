package com.rcplus.wanted.dtos;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

public class GetUserSpecialtiesDto {

    @Getter
    @Setter
    @Builder
    public static class Response {
        @JsonProperty("job_field")
        private String jobField;
        @JsonProperty("job_specialties")
        private List<String> jobSpecialties;
        private int years;
    }

}
