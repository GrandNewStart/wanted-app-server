package com.rcplus.wanted.services;

import com.rcplus.wanted.configs.BaseException;
import com.rcplus.wanted.dtos.LogInDto;
import com.rcplus.wanted.dtos.RefreshTokenDto;
import com.rcplus.wanted.dtos.SignOutDto;
import com.rcplus.wanted.dtos.SignUpDto;
import com.rcplus.wanted.models.User;

public interface UserService {
    public User findById(Long userId);
    public User findByEmail(String email);
    public SignUpDto.Response createUser(SignUpDto.Request request) throws BaseException;
    public void deleteUser(SignOutDto.Request request) throws BaseException;
    public LogInDto.Response logIn(LogInDto.Request request) throws BaseException;
    public RefreshTokenDto.Response refreshToken(RefreshTokenDto.Request request, String auth) throws BaseException;
}
