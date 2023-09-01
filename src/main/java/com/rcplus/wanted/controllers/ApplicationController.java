package com.rcplus.wanted.controllers;

import static com.rcplus.wanted.configs.BaseResponseStatus.*;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.rcplus.wanted.configs.BaseException;
import com.rcplus.wanted.configs.BaseResponse;
import com.rcplus.wanted.dtos.AppliedApplicationsDto;
import com.rcplus.wanted.models.Application;
import com.rcplus.wanted.services.ApplicationService;


@RestController
public class ApplicationController {

    @Autowired
    private ApplicationService applicationService;

    @GetMapping("/application/status")
    public BaseResponse getApplications(@RequestBody Long userId){
        List<Application> applications = applicationService.getApplications(userId);
        AppliedApplicationsDto.Response response = AppliedApplicationsDto.Response.builder().applications(applications).build();
        return new BaseResponse(response);
        
    }

    @DeleteMapping("/application/status")
    public BaseResponse deleteApplication(@RequestBody Long applicationId){
        try{
            applicationService.deleteApplication(applicationId);
            return new BaseResponse(SUCCESS);
        }
        catch(BaseException e){
            return new BaseResponse(e.getStatus());
        }
    }

    @PatchMapping("/application/status")
    public BaseResponse changeStatus(@RequestBody Long applicationId, @RequestBody String status){
        try{
            applicationService.changeStatus(applicationId, status);
            return new BaseResponse(SUCCESS);
        }
        catch(BaseException e){
            return new BaseResponse(e.getStatus());
        }
    }


    
}
