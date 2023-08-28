package com.rcplus.wanted.controllers;

import java.net.http.HttpHeaders;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.rcplus.wanted.configs.BaseException;
import com.rcplus.wanted.configs.BaseResponse;
import com.rcplus.wanted.dtos.LogInDto;
import com.rcplus.wanted.dtos.RefreshTokenDto;
import com.rcplus.wanted.dtos.SignOutDto;
import com.rcplus.wanted.dtos.SignUpDto;
import com.rcplus.wanted.services.UserService;

import static com.rcplus.wanted.configs.BaseResponseStatus.*;

@RestController
public class UserController {

    @Autowired
    private UserService userService;
    
    @PostMapping("/users")
    public BaseResponse createUser(@RequestBody SignUpDto.Request request) {
        try {
            SignUpDto.Response data = this.userService.createUser(request);
            return new BaseResponse(data);
        } catch (BaseException e) {
            return new BaseResponse(e.getStatus());
        }
    }

    @DeleteMapping("/users")
    public BaseResponse deleteUser(@RequestBody SignOutDto.Request request) {
        try {
            this.userService.deleteUser(request);
            return new BaseResponse(SUCCESS);
        } catch (BaseException e) {
            return new BaseResponse(e.getStatus());
        }
    }

    @PostMapping("/login")
    public BaseResponse login(@RequestBody LogInDto.Request request) {
        try {
            LogInDto.Response data = this.userService.logIn(request);
            return new BaseResponse(data);
        } catch (BaseException e) {
            return new BaseResponse(e.getStatus());
        }
    }

    @PostMapping("/token-refresh")
    public BaseResponse refreshToken(@RequestBody RefreshTokenDto.Request request, @RequestHeader("Authorization") String auth) {
        try {
            RefreshTokenDto.Response data = this.userService.refreshToken(request, auth);
            return new BaseResponse(data);
        } catch (BaseException e) {
            return new BaseResponse(e.getStatus());
        }
    }

}
