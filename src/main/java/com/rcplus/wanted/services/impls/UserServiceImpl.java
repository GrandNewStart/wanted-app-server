package com.rcplus.wanted.services.impls;


import static com.rcplus.wanted.configs.BaseResponseStatus.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import com.rcplus.wanted.configs.BaseException;
import com.rcplus.wanted.configs.jwt.TokenService;
import com.rcplus.wanted.dtos.GetUserInfoDto;
import com.rcplus.wanted.dtos.GetUserSpecialtiesDto;
import com.rcplus.wanted.dtos.SignOutDto;
import com.rcplus.wanted.dtos.SignUpDto;
import com.rcplus.wanted.dtos.UpdateSpecialtiesDto;
import com.rcplus.wanted.dtos.UpdateUserInfoDto;
import com.rcplus.wanted.models.User;
import com.rcplus.wanted.repositories.UserRepository;
import com.rcplus.wanted.services.UserService;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TokenService tokenService;

    @Override
    public SignUpDto.Response createUser(SignUpDto.Request request) throws BaseException {
        Optional<User> existingUser = this.userRepository.findByEmail(request.getEmail());
        if (existingUser.isPresent()) {
            throw new BaseException(DUPLICATED_EMAIL);
        }
        User user = this.userRepository.save(request.toUser());
        return SignUpDto.Response.builder()
            .id(user.getId())
            .name(user.getName())
            .build();
    }

    @Override
    public GetUserInfoDto.Response getUser(HttpHeaders headers) throws BaseException {
        try {
            User user = this.tokenService.getUserFromHttpHeaders(headers);
            return GetUserInfoDto.Response.from(user);
        } catch (BaseException e) {
            throw e;
        }
    }
    
    @Override
    public void updateUser(HttpHeaders headers, UpdateUserInfoDto.Request request) throws BaseException {
        User user;
        try {
            user = this.tokenService.getUserFromHttpHeaders(headers);
        } catch (BaseException e) {
            throw e;
        }
        if (!user.getId().equals(request.getId())) {
            throw new BaseException(USER_ID_EMPTY);
        }
        if (request.getName().isEmpty()) {
            throw new BaseException(USER_NAME_EMPTY);
        }
        if (request.getCountryCode().isEmpty()) {
            throw new BaseException(USER_NAME_EMPTY);
        }
        if (request.getPhoneNumber().isEmpty()) {
            throw new BaseException(USER_NAME_EMPTY);
        }
        User updatedUser = user;
        updatedUser.setName(request.getName());
        updatedUser.setCountryCode(request.getCountryCode());
        updatedUser.setPhoneNumber(request.getPhoneNumber());
        updatedUser.setMarketingEmail(request.getMarketingEmail().equals("Y"));
        updatedUser.setMarketingPush(request.getMarketingPush().equals("Y"));
        updatedUser.setMarketingSms(request.getMarketingSms().equals("Y"));
        this.userRepository.save(updatedUser);
        return;
    }

    @Override
    public void deleteUser(HttpHeaders headers, SignOutDto.Request request) throws BaseException {
        User user;
        try {
            user = this.tokenService.getUserFromHttpHeaders(headers);
        } catch (BaseException e) {
            throw e;
        }
        if (user.getId() != request.getUserId()) {
            throw new BaseException(REQUEST_ERROR);
        }
        if (!user.getEmail().equals(request.getEmail())) {
            throw new BaseException(REQUEST_ERROR);
        }
        if (!user.getPassword().equals(request.getPassword())) {
            throw new BaseException(REQUEST_ERROR);
        }
        this.userRepository.delete(user);
    }

    @Override
    public User findById(Long userId) throws BaseException {
        Optional<User> user = this.userRepository.findById(userId);
        if (!user.isPresent()) {
            throw new BaseException(REQUEST_ERROR);
        }
        return user.get();
    }

    @Override
    public User findByEmail(String email) throws BaseException {
        Optional<User> user = this.userRepository.findByEmail(email);
        if (!user.isPresent()) {
            throw new BaseException(REQUEST_ERROR);
        }
        return user.get();
    }

    @Override
    public GetUserSpecialtiesDto.Response getUserSpecialties(HttpHeaders headers) throws BaseException {
        User user;
        try {
            user = this.tokenService.getUserFromHttpHeaders(headers);
        } catch (BaseException e) {
            throw e;
        }
        List<String> specialties = new ArrayList<>();
        for (String value : user.getJobSpecialties().split(";")) {
            specialties.add(value);
        }
        return GetUserSpecialtiesDto.Response.builder()
            .jobField(user.getJobField())
            .jobSpecialties(specialties)
            .years(user.getYears())
            .build();
    }

    @Override
    public void updateUserSpecialties(HttpHeaders headers, UpdateSpecialtiesDto.Request request) throws BaseException {
        User user;
        try {
            user = this.tokenService.getUserFromHttpHeaders(headers);
        } catch (BaseException e) {
            throw e;
        }
        User updatedUser = user;
        String specialties = "";
        for (String value : request.getJobSpecialties()) {
            specialties += value;
            specialties += ";";
        }

        updatedUser.setJobField(request.getJobField());
        updatedUser.setJobSpecialties(specialties);
        updatedUser.setYears(request.getYears());
        this.userRepository.save(updatedUser);
        return;
    }

}
