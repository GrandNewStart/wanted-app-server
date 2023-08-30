package com.rcplus.wanted.dtos;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

public class UpdateSpecialtiesDto {
    
    @Getter
    @Setter
    public static class Request {
        @JsonProperty("job_field")
        private String jobField;
        @JsonProperty("job_specialties")
        private List<String> jobSpecialties;
        private int years;
    }

}
