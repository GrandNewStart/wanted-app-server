package com.rcplus.wanted.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.rcplus.wanted.models.User;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

public class GetUserInfoDto {
    
    @Getter
    @Setter
    @AllArgsConstructor
    public static class Response {
        private Long id;
        private String email;
        private String name;
        private String image;
        @JsonProperty("country_code")
        private String countryCode;
        @JsonProperty("phone_number")
        private String phoneNumber;
        @JsonProperty("marketing_email")
        private Boolean marketingEmail;
        @JsonProperty("marketing_push")
        private Boolean marketingPush;
        @JsonProperty("marketing_sms")
        private Boolean marketingSms;

        public static Response from(User user) {
            return new Response(
                user.getId(),
                user.getEmail(),
                user.getName(),
                user.getImage(),
                user.getCountryCode(),
                user.getPhoneNumber(),
                user.getMarketingEmail(),
                user.getMarketingPush(),
                user.getMarketingSms()
            );
        }
    }

}
