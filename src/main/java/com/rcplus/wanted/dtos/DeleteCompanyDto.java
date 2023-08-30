package com.rcplus.wanted.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

public class DeleteCompanyDto {
    
    @Getter
    @Setter
    public static class Request {
        @JsonProperty("user_id")
        private Long userId;

        @JsonProperty("company_id")
        private Long companyId;

        @JsonProperty("password")
        private String password;
    }

}
