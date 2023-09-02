package com.rcplus.wanted.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.rcplus.wanted.models.Company;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

public class GetCompanyInfoDto {
    
    @Getter
    @Setter
    @Builder
    public static class Response {

        @JsonProperty("id")
        private Long id;

        @JsonProperty("user_id")
        private Long userId;

        private String name;

        @JsonProperty("business_reg_code")
        private String businessRegCode;

        private String image;

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

        public static GetCompanyInfoDto.Response from(Company company) {
            return GetCompanyInfoDto.Response.builder()
                .id(company.getId())
                .userId(company.getUserId())
                .name(company.getName())
                .image(company.getImage())
                .businessRegCode(company.getBusinessRegCode())
                .nation(company.getNation())
                .region(company.getRegion())
                .employeeCount(company.getEmployeeCount())
                .marketingEmail(company.getMarketingEmail())
                .marketingPush(company.getMarketingPush())
                .marketingSms(company.getMarketingSms())
                .build();
        }

    }

}
