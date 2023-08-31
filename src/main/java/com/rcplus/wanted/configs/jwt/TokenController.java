package com.rcplus.wanted.configs.jwt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.rcplus.wanted.configs.BaseException;
import com.rcplus.wanted.configs.BaseResponse;
import com.rcplus.wanted.dtos.LogInDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class TokenController {
    
    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public BaseResponse login(@RequestBody LogInDto.Request request) {
        try {
            LogInDto.Response data = this.tokenService.createNewTokens(request);
            return new BaseResponse(data);
        } catch (BaseException e) {
            return new BaseResponse(e.getStatus());
        }
    }

    @PostMapping("/token-refresh")
    public BaseResponse createNewAccessToken(@RequestBody CreateAccessTokenDto.Request request) {
        try {
            CreateAccessTokenDto.Response data = this.tokenService.refreshTokens(request);
            return new BaseResponse(data);
        } catch (BaseException e) {
            return new BaseResponse(e.getStatus());
        }
        
    }

}
