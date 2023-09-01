package com.rcplus.wanted.services;

import java.util.List;

import com.rcplus.wanted.configs.BaseException;
import com.rcplus.wanted.models.Application;

public interface ApplicationService {
    public boolean checkApplicationExists(Long userId, Long recruitmentId);
    public List<Application> getApplications(Long userId);
    public void deleteApplication(Long applicationId) throws BaseException;
    public void changeStatus(Long applicationId, String status) throws BaseException;

}
