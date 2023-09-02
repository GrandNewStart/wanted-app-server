package com.rcplus.wanted.services;

import java.util.List;

import org.springframework.http.HttpHeaders;

import com.rcplus.wanted.configs.BaseException;
import com.rcplus.wanted.dtos.ApplicationDto;
import com.rcplus.wanted.dtos.ChangeApplicationStatusDto;
import com.rcplus.wanted.dtos.DeleteApplicationDto;
import com.rcplus.wanted.dtos.PostApplicationDto;

public interface ApplicationService {
    public List<ApplicationDto> getApplications(HttpHeaders headers) throws BaseException;
    public ApplicationDto getApplication(HttpHeaders headers, Long recruitmentId) throws BaseException;
    public void applyToRecruitment(HttpHeaders headers, PostApplicationDto.Request request) throws BaseException;
    public void deleteApplication(HttpHeaders headers, DeleteApplicationDto.Request request) throws BaseException;
    public void changeStatus(HttpHeaders headers, ChangeApplicationStatusDto.Request request) throws BaseException;

}
