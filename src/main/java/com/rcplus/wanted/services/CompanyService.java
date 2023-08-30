package com.rcplus.wanted.services;

import org.springframework.http.HttpHeaders;

import com.rcplus.wanted.configs.BaseException;
import com.rcplus.wanted.dtos.DeleteCompanyDto;
import com.rcplus.wanted.dtos.GetCompanyInfoDto;
import com.rcplus.wanted.dtos.RegisterCompanyDto;

public interface CompanyService {
    public RegisterCompanyDto.Response registerCompany(HttpHeaders headers, RegisterCompanyDto.Request request) throws BaseException;   
    public GetCompanyInfoDto.Response getCompany(HttpHeaders headers, Long companyId) throws BaseException;
    public void deleteCompany(HttpHeaders headers, DeleteCompanyDto.Request request) throws BaseException;
}
