package com.rcplus.wanted.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

public class UpdateUserInfoDto {
    
    @Getter
    @Setter
    public static class Request {
        private Long id;
        private String name;
        @JsonProperty("country_code")
        private String countryCode;
        @JsonProperty("phone_number")
        private String phoneNumber;
        @JsonProperty("marketing_email")
        private String marketingEmail;
        @JsonProperty("marketing_push")
        private String marketingPush;
        @JsonProperty("marketing_sms")
        private String marketingSms;
    }

}
