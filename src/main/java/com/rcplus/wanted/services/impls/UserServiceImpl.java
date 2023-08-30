package com.rcplus.wanted.services.impls;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import com.rcplus.wanted.configs.BaseException;
import com.rcplus.wanted.dtos.GetUserSpecialtiesDto;
import com.rcplus.wanted.dtos.GetUserInfoDto;
import com.rcplus.wanted.dtos.LogInDto;
import com.rcplus.wanted.dtos.RefreshTokenDto;
import com.rcplus.wanted.dtos.SignOutDto;
import com.rcplus.wanted.dtos.SignUpDto;
import com.rcplus.wanted.dtos.UpdateSpecialtiesDto;
import com.rcplus.wanted.dtos.UpdateUserInfoDto;
import com.rcplus.wanted.models.User;
import com.rcplus.wanted.repositories.UserRepository;
import com.rcplus.wanted.services.UserService;

import static com.rcplus.wanted.configs.BaseResponseStatus.*;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

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
            String[] credentials = this.getUserCredentials(headers);
            User user = this.getUserFromCredentials(credentials);
            return GetUserInfoDto.Response.from(user);
        } catch (BaseException e) {
            throw e;
        }
    }

    
    @Override
    public void updateUser(HttpHeaders headers, UpdateUserInfoDto.Request request) throws BaseException {
        User user;
        try {
            String[] credentials = this.getUserCredentials(headers);
            user = this.getUserFromCredentials(credentials);
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
            String[] credentials = this.getUserCredentials(headers);
            user = this.getUserFromCredentials(credentials);
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
    public User findById(Long userId) {
        return this.userRepository.findById(userId)
            .orElseThrow(()->new IllegalArgumentException("Unexpected user"));
    }

    @Override
    public User findByEmail(String email) {
        return this.userRepository.findByEmail(email)
            .orElseThrow(()->new IllegalArgumentException(email));
    }

    @Override
    public GetUserSpecialtiesDto.Response getUserSpecialties(HttpHeaders headers) throws BaseException {
        User user;
        try {
            String[] credentials = this.getUserCredentials(headers);
            user = this.getUserFromCredentials(credentials);
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
            String[] credentials = this.getUserCredentials(headers);
            user = this.getUserFromCredentials(credentials);
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

    @Override
    public LogInDto.Response logIn(LogInDto.Request request) throws BaseException {
        String email = request.getEmail();
        if (email.isEmpty()) {
            throw new BaseException(FAILED_TO_LOGIN);
        }
        Optional<User> user = this.userRepository.findByEmail(email);
        if (!user.isPresent()) {
            throw new BaseException(FAILED_TO_LOGIN);
        }
        if (!user.get().getPassword().equals(request.getPassword())) {
            throw new BaseException(FAILED_TO_LOGIN);
        }
        String userCredential = user.get().getEmail() + ":" + user.get().getPassword();
        String token = Base64.getEncoder().encodeToString(userCredential.getBytes());
        return LogInDto.Response.builder()
            .accessToken(token)
            .refreshToken(token)
            .expireTime(3600)
            .build();
    }

    @Override
    public RefreshTokenDto.Response refreshToken(RefreshTokenDto.Request request, HttpHeaders headers) throws BaseException {
        User user;
        try {
            String[] credentials = this.getUserCredentials(headers);
            user = this.getUserFromCredentials(credentials);
        } catch (BaseException e) {
            throw e;
        }
        String userCredential = user.getEmail() + ":" + user.getPassword();
        String newToken = Base64.getEncoder().encodeToString(userCredential.getBytes());
        return RefreshTokenDto.Response.builder()
            .accessToken(newToken)
            .refreshToken(newToken)
            .expireTime(3600)
            .build();
    }

    private String[] getUserCredentials(HttpHeaders headers) throws BaseException {
        List<String> authHeaders = headers.get("authorization");
        if (authHeaders == null) {
            throw new BaseException(INVALID_JWT);
        }
        String auth = authHeaders.get(0);
        if (auth == null) {
            throw new BaseException(INVALID_JWT);
        }
        String[] auths = auth.split(" ");
        if (auths.length != 2) {
            throw new BaseException(INVALID_JWT);
        }
        if (!auths[0].equals("Basic")) {
            throw new BaseException(INVALID_JWT);
        }
        String credentialString = new String(Base64.getDecoder().decode(auths[1]));
        String[] credentials = credentialString.split(":");
        if (credentials.length != 2) {
            throw new BaseException(INVALID_JWT);
        }
        return credentials;
    }

    private User getUserFromCredentials(String[] credentials) throws BaseException {
        String email = credentials[0];
        String password = credentials[1];
        Optional<User> user = this.userRepository.findByEmail(email);
        if (user.isEmpty()) {
            throw new BaseException(INVALID_JWT);
        }
        if (!user.get().getPassword().equals(password)) {
            throw new BaseException(INVALID_JWT);
        }
        return user.get();
    }

}
