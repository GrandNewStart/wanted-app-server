package com.rcplus.wanted.controllers;

import static com.rcplus.wanted.configs.BaseResponseStatus.*;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.rcplus.wanted.configs.BaseException;
import com.rcplus.wanted.configs.BaseResponse;
import com.rcplus.wanted.dtos.ApplicationDto;
import com.rcplus.wanted.dtos.ApplicationListDto;
import com.rcplus.wanted.dtos.ChangeApplicationStatusDto;
import com.rcplus.wanted.dtos.DeleteApplicationDto;
import com.rcplus.wanted.dtos.PostApplicationDto;
import com.rcplus.wanted.services.ApplicationService;

@RestController
public class ApplicationController {

    @Autowired
    private ApplicationService applicationService;

    @GetMapping("/applications")
    public ResponseEntity<BaseResponse> getApplications(@RequestHeader HttpHeaders headers){
        try {
            List<ApplicationDto> applications = this.applicationService.getApplications(headers);
            ApplicationListDto data = ApplicationListDto.builder().applications(applications).build();
            BaseResponse response = new BaseResponse(data);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (BaseException e) {
            BaseResponse response = new BaseResponse(e.getStatus());
            return new ResponseEntity<>(response, e.getStatus().getStatus());
        }
    }


    @PostMapping("/applications")
    public ResponseEntity<BaseResponse> applyToRecruitment(@RequestHeader HttpHeaders headers, @RequestBody PostApplicationDto.Request request){
        try {
            this.applicationService.applyToRecruitment(headers, request);
            BaseResponse response = new BaseResponse(SUCCESS);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (BaseException e) {
            BaseResponse response = new BaseResponse(e.getStatus());
            return new ResponseEntity<>(response, e.getStatus().getStatus());
        }
    }

    @PatchMapping("/applications")
    public ResponseEntity<BaseResponse> changeStatus(@RequestHeader HttpHeaders headers, @RequestBody ChangeApplicationStatusDto.Request request) {
        try {
            this.applicationService.changeStatus(headers, request);
            BaseResponse response = new BaseResponse(SUCCESS);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (BaseException e) {
            BaseResponse response = new BaseResponse(e.getStatus());
            return new ResponseEntity<>(response, e.getStatus().getStatus());
        }
    }

    @DeleteMapping("/applications")
    public ResponseEntity<BaseResponse> deleteApplication(@RequestHeader HttpHeaders headers, @RequestBody DeleteApplicationDto.Request request){
        try {
            this.applicationService.deleteApplication(headers, request);
            BaseResponse response = new BaseResponse(SUCCESS);
            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (BaseException e) {
            BaseResponse response = new BaseResponse(e.getStatus());
            return new ResponseEntity<>(response, e.getStatus().getStatus());
        }
    }
    
}
