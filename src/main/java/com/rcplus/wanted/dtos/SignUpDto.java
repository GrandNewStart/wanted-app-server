package com.rcplus.wanted.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.rcplus.wanted.models.JobField;
import com.rcplus.wanted.models.JobSpecialty;
import com.rcplus.wanted.models.User;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


public class SignUpDto {
    
    @Getter
    @Setter
    public static class Request {
        private String email;
        private String password;
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

        public User toUser() {
            return User.builder()
                .email(email)
                .password(password)
                .name(name)
                .countryCode(countryCode)
                .phoneNumber(phoneNumber)
                .marketingEmail(marketingEmail == "Y")
                .marketingPush(marketingPush == "Y")
                .marketingSms(marketingSms == "Y")
                .authority("USER")
                .image("")
                .jobField(JobField.ALL.getName())
                .jobSpecialties(JobSpecialty.선택안함.getName())
                .build();
        }
    }

    @Getter
    @Setter
    @Builder
    public static class Response {
        private Long id;
        private String name;
    }

}
