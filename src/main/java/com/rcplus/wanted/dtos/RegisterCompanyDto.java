package com.rcplus.wanted.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.rcplus.wanted.models.Company;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

public class RegisterCompanyDto {
    
    @Getter
    @Setter
    public static class Request {
        
        private String name;

        @JsonProperty("business_reg_code")
        private String businessRegCode;

        private String industry;

        private String nation;
        
        private String region;

        @JsonProperty("employee_count")
        private String employeeCount;

        @JsonProperty("marketing_email")
        private String marketingEmail;

        @JsonProperty("marketing_push")
        private String marketingPush;

        @JsonProperty("marketing_sms")
        private String marketingSms;

        public Company toCompany(Long userId) {
            return Company.builder()
                .name(name)
                .userId(userId)
                .businessRegCode(businessRegCode)
                .industry(industry)
                .nation(nation)
                .region(region)
                .image("https://wanted-resources.s3.ap-northeast-2.amazonaws.com/companies/default.jpg")
                .employeeCount(employeeCount)
                .marketingEmail(marketingEmail)
                .marketingPush(marketingPush)
                .marketingSms(marketingSms)
                .build();
        }

    }

    @AllArgsConstructor
    public static class Response {
        @JsonProperty("company_id")
        private Long id;
    }

}
