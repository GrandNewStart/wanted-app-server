package com.rcplus.wanted.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rcplus.wanted.configs.BaseException;
import com.rcplus.wanted.configs.BaseResponse;
import com.rcplus.wanted.dtos.DeleteCompanyDto;
import com.rcplus.wanted.dtos.GetCompanyInfoDto;
import com.rcplus.wanted.dtos.RegisterCompanyDto;
import com.rcplus.wanted.services.CompanyService;

import static com.rcplus.wanted.configs.BaseResponseStatus.*;

@RestController
public class CompanyController {
    
    @Autowired
    private CompanyService companyService;

    @GetMapping("/companies")
    public BaseResponse getCompany(@RequestHeader HttpHeaders headers, @RequestParam("id") Long id) {
        try {
            GetCompanyInfoDto.Response data = this.companyService.getCompany(headers, id);
            return new BaseResponse(data);
        } catch (BaseException e) {
            return new BaseResponse(e.getStatus());
        }
    }

    @PostMapping("/companies")
    public BaseResponse registerCompany(@RequestHeader HttpHeaders headers, @RequestBody RegisterCompanyDto.Request request) {
        try {
            RegisterCompanyDto.Response data = this.companyService.registerCompany(headers, request);
            return new BaseResponse(data);
        } catch (BaseException e) {
            return new BaseResponse(e.getStatus());
        }
    }

    @DeleteMapping("/companies")
    public BaseResponse deleteCompany(@RequestHeader HttpHeaders headers, @RequestBody DeleteCompanyDto.Request request) {
        try {
            this.companyService.deleteCompany(headers, request);
            return new BaseResponse(SUCCESS);
        } catch (BaseException e) {
            return new BaseResponse(e.getStatus());
        }
    }

}
