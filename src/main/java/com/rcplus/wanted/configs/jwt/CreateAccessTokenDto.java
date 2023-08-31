package com.rcplus.wanted.configs.jwt;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

public class CreateAccessTokenDto {
    
    @Getter
    @Setter
    public static class Request {
        @JsonProperty("refresh_token")
        private String refreshToken;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    public static class Response {
        @JsonProperty("access_token")
        private String accessToken;
        @JsonProperty("refresh_token")
        private String refreshToken;
    }

}
