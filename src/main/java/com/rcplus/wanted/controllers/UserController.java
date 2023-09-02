package com.rcplus.wanted.controllers;


import static com.rcplus.wanted.configs.BaseResponseStatus.*;

import java.io.IOException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.rcplus.wanted.configs.BaseException;
import com.rcplus.wanted.configs.BaseResponse;
import com.rcplus.wanted.dtos.GetCompanyInfoDto;
import com.rcplus.wanted.dtos.GetUserSpecialtiesDto;
import com.rcplus.wanted.dtos.SignOutDto;
import com.rcplus.wanted.dtos.SignUpDto;
import com.rcplus.wanted.dtos.UpdateSpecialtiesDto;
import com.rcplus.wanted.dtos.UpdateUserInfoDto;
import com.rcplus.wanted.services.CompanyService;
import com.rcplus.wanted.services.UserService;


@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private CompanyService companyService;
    
    @PostMapping("/users")
    public ResponseEntity<BaseResponse> createUser(@RequestBody SignUpDto.Request request) {
        try {
            BaseResponse data = new BaseResponse(this.userService.createUser(request));
            return new ResponseEntity<BaseResponse>(data, HttpStatus.OK);
        } catch (BaseException e) {
            BaseResponse data = new BaseResponse(e.getStatus());
            return new ResponseEntity<BaseResponse>(data, e.getStatus().getStatus());
        } 
    }

    @GetMapping("/users")
    public ResponseEntity<BaseResponse> getUser(@RequestHeader HttpHeaders headers) {
        try {
            BaseResponse data = new BaseResponse(this.userService.getUser(headers));
            return new ResponseEntity<BaseResponse>(data, HttpStatus.OK);
        } catch (BaseException e) {
            BaseResponse data = new BaseResponse(e.getStatus());
            return new ResponseEntity<BaseResponse>(data, e.getStatus().getStatus());
        }
    }

    @PatchMapping("/users")
    public ResponseEntity<BaseResponse> updateUser(@RequestHeader HttpHeaders headers, @RequestBody UpdateUserInfoDto.Request request) {
        try {
            this.userService.updateUser(headers, request);
            return new ResponseEntity<BaseResponse>(new BaseResponse(SUCCESS), HttpStatus.OK);
        } catch (BaseException e) {
            BaseResponse data = new BaseResponse(e.getStatus());
            return new ResponseEntity<BaseResponse>(data, e.getStatus().getStatus());
        }
    }

    @DeleteMapping("/users")
    public ResponseEntity<BaseResponse> deleteUser(@RequestHeader HttpHeaders headers, @RequestBody SignOutDto.Request request) {
        try {
            this.userService.deleteUser(headers, request);
            return new ResponseEntity<BaseResponse>(new BaseResponse(SUCCESS), HttpStatus.OK);
        } catch (BaseException e) {
            BaseResponse data = new BaseResponse(e.getStatus());
            return new ResponseEntity<BaseResponse>(data, e.getStatus().getStatus());
        }
    }

    @GetMapping("/users/specialties")
    public ResponseEntity<BaseResponse> getUserSpecialties(@RequestHeader HttpHeaders headers) {
        try {
            GetUserSpecialtiesDto.Response data = this.userService.getUserSpecialties(headers);
            return new ResponseEntity<BaseResponse>(new BaseResponse(data), HttpStatus.OK);
        } catch (BaseException e) {
            BaseResponse data = new BaseResponse(e.getStatus());
            return new ResponseEntity<BaseResponse>(data, e.getStatus().getStatus());
        }
    }

    @PostMapping("/users/specialties")
    public ResponseEntity<BaseResponse> updateUserSpecialties(@RequestHeader HttpHeaders headers, @RequestBody UpdateSpecialtiesDto.Request request) {
        try {
            this.userService.updateUserSpecialties(headers, request);
            return new ResponseEntity<BaseResponse>(new BaseResponse(SUCCESS), HttpStatus.OK);
        } catch (BaseException e) {
            BaseResponse data = new BaseResponse(e.getStatus());
            return new ResponseEntity<BaseResponse>(data, e.getStatus().getStatus());
        }
    }

    @GetMapping("/users/companies")
    public ResponseEntity<BaseResponse> getUserCompany(@RequestHeader HttpHeaders headers) {
        try {
            GetCompanyInfoDto.Response data = this.companyService.getUserCompany(headers);
            if (data == null) {
                return new ResponseEntity<BaseResponse>(new BaseResponse(SUCCESS), HttpStatus.OK);
            } else {
                return new ResponseEntity<BaseResponse>(new BaseResponse(data), HttpStatus.OK);
            }
        } catch (BaseException e) {
            BaseResponse data = new BaseResponse(e.getStatus());
            return new ResponseEntity<BaseResponse>(data, e.getStatus().getStatus());
        }
    }

    @PutMapping("/users/images")
    public ResponseEntity<BaseResponse> uploadUserImage(@RequestHeader HttpHeaders headers, @RequestParam("file") MultipartFile file) {
        try {
            this.userService.updloadUserImage(headers, file);
            BaseResponse response = new BaseResponse(SUCCESS);
            return new ResponseEntity<BaseResponse>(response, HttpStatus.OK);
        } catch (BaseException e) {
            BaseResponse response = new BaseResponse(e.getStatus());
            return new ResponseEntity<BaseResponse>(response, e.getStatus().getStatus());
        }
    }

}
