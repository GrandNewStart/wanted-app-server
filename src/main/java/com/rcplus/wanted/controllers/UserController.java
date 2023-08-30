package com.rcplus.wanted.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.rcplus.wanted.configs.BaseException;
import com.rcplus.wanted.configs.BaseResponse;
import com.rcplus.wanted.dtos.GetUserInfoDto;
import com.rcplus.wanted.dtos.LogInDto;
import com.rcplus.wanted.dtos.RefreshTokenDto;
import com.rcplus.wanted.dtos.SignOutDto;
import com.rcplus.wanted.dtos.SignUpDto;
import com.rcplus.wanted.dtos.UpdateUserInfoDto;
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

    @GetMapping("/users")
    public BaseResponse getUser(@RequestHeader HttpHeaders headers) {
        try {
            GetUserInfoDto.Response data = this.userService.getUser(headers);
            return new BaseResponse(data);
        } catch (BaseException e) {
            return new BaseResponse(e.getStatus());
        }
    }

    @PatchMapping("/users")
    public BaseResponse updateUser(@RequestHeader HttpHeaders headers, @RequestBody UpdateUserInfoDto.Request request) {
        try {
            this.userService.updateUser(headers, request);
            return new BaseResponse(SUCCESS);
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
    public BaseResponse refreshToken(@RequestBody RefreshTokenDto.Request request, @RequestHeader HttpHeaders headers) {
        try {
            RefreshTokenDto.Response data = this.userService.refreshToken(request, headers);
            return new BaseResponse(data);
        } catch (BaseException e) {
            return new BaseResponse(e.getStatus());
        }
    }

}
