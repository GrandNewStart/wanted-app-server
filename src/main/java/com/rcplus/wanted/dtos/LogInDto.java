package com.rcplus.wanted.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

public class LogInDto {

    @Getter
    @Setter
    public static class Request {
        private String email;
        private String password;
    }

    @Getter
    @Setter
    @Builder
    public static class Response {
        @JsonProperty("access_token")
        private String accessToken;

        @JsonProperty("refresh_token")
        private String refreshToken;

        @JsonProperty("expire_time")
        private Integer expireTime;
    }
    
}
