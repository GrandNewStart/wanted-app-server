package com.rcplus.wanted.services;

import org.springframework.http.HttpHeaders;

import com.rcplus.wanted.configs.BaseException;
import com.rcplus.wanted.dtos.GetUserInfoDto;
import com.rcplus.wanted.dtos.GetUserSpecialtiesDto;
import com.rcplus.wanted.dtos.LogInDto;
import com.rcplus.wanted.dtos.RefreshTokenDto;
import com.rcplus.wanted.dtos.SignOutDto;
import com.rcplus.wanted.dtos.SignUpDto;
import com.rcplus.wanted.dtos.UpdateSpecialtiesDto;
import com.rcplus.wanted.dtos.UpdateUserInfoDto;
import com.rcplus.wanted.models.User;

public interface UserService {
    public User findById(Long userId);
    public User findByEmail(String email);
    public SignUpDto.Response createUser(SignUpDto.Request request) throws BaseException;
    public GetUserInfoDto.Response getUser(HttpHeaders headers) throws BaseException;
    public void updateUser(HttpHeaders headers, UpdateUserInfoDto.Request request) throws BaseException;
    public void deleteUser(SignOutDto.Request request) throws BaseException;
    public GetUserSpecialtiesDto.Response getUserSpecialties(HttpHeaders headers) throws BaseException;
    public void updateUserSpecialties(HttpHeaders headers, UpdateSpecialtiesDto.Request request) throws BaseException;
    public LogInDto.Response logIn(LogInDto.Request request) throws BaseException;
    public RefreshTokenDto.Response refreshToken(RefreshTokenDto.Request request, HttpHeaders headers) throws BaseException;
}
