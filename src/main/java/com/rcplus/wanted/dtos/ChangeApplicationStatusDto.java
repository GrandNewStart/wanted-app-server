package com.rcplus.wanted.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

public class ChangeApplicationStatusDto {
    
    @Getter
    @Setter
    public static class Request {
        @JsonProperty("application_id")
        private Long applicationId;
        private String status;
    }

}
