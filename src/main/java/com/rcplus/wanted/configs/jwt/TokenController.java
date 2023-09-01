package com.rcplus.wanted.configs.jwt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<BaseResponse> login(@RequestBody LogInDto.Request request) {
        try {
            BaseResponse data = new BaseResponse(this.tokenService.createNewTokens(request));
            return new ResponseEntity<>(data, HttpStatus.OK);
        } catch (BaseException e) {
            BaseResponse data = new BaseResponse(e.getStatus());
            return new ResponseEntity<>(data, e.getStatus().getStatus());
        }
    }

    @PostMapping("/token-refresh")
    public ResponseEntity<BaseResponse> createNewAccessToken(@RequestBody CreateAccessTokenDto.Request request) {
        try {
            BaseResponse data = new BaseResponse(this.tokenService.refreshTokens(request));
            return new ResponseEntity<>(data, HttpStatus.OK);
        } catch (BaseException e) {
            BaseResponse data = new BaseResponse(e.getStatus());
            return new ResponseEntity<BaseResponse>(data, e.getStatus().getStatus());
        }
        
    }

}
