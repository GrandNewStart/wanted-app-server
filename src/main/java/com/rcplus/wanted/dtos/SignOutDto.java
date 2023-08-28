package com.rcplus.wanted.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

public class SignOutDto {
    
    @Getter
    @Setter
    public static class Request {
        @JsonProperty("user_id")
        private Long userId;
        private String email;
        private String password;
    }

}
