package com.rcplus.wanted.services.impls;

import static com.rcplus.wanted.configs.BaseResponseStatus.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import com.rcplus.wanted.configs.BaseException;
import com.rcplus.wanted.configs.jwt.TokenService;
import com.rcplus.wanted.dtos.ApplicationDto;
import com.rcplus.wanted.dtos.ChangeApplicationStatusDto;
import com.rcplus.wanted.dtos.DeleteApplicationDto;
import com.rcplus.wanted.dtos.PostApplicationDto;
import com.rcplus.wanted.models.Application;
import com.rcplus.wanted.models.User;
import com.rcplus.wanted.repositories.ApplicationRepository;
import com.rcplus.wanted.repositories.CompanyRepository;
import com.rcplus.wanted.repositories.RecruitmentRepository;
import com.rcplus.wanted.services.ApplicationService;
import com.rcplus.wanted.models.ApplicationStatus;
import com.rcplus.wanted.models.Company;
import com.rcplus.wanted.models.Recruitment;

@Service
public class ApplicationServiceImpl implements ApplicationService{

    @Autowired
    private TokenService tokenService;

    @Autowired
    private ApplicationRepository applicationRepository;

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private RecruitmentRepository recruitmentRepository;

    @Override
    public List<ApplicationDto> getApplications(HttpHeaders headers) throws BaseException {
        User user;
        try {
            user = this.tokenService.getUserFromHttpHeaders(headers);
        } catch (BaseException e) {
            throw e;
        }
        try {
            return this.applicationRepository
                .findByUserId(user.getId())
                .stream()
                .map(e->ApplicationDto.from(e))
                .toList();
        } catch (Exception e) {
            throw new BaseException(UNKNOWN_ERROR);
        }
    }

    @Override
    public ApplicationDto getApplication(HttpHeaders headers, Long recruitmentId) throws BaseException {
        User user;
        try {
            user = this.tokenService.getUserFromHttpHeaders(headers);
        } catch (BaseException e) {
            throw e;
        }
        Optional<Application> applicationOpt = applicationRepository.findByUserIdAndRecruitmentId(user.getId(), recruitmentId);
        if (applicationOpt.isEmpty()) {
            throw new BaseException(NO_APPLICATION_FOUND);
        }
        return ApplicationDto.from(applicationOpt.get());
    }

    @Override
    public void applyToRecruitment(HttpHeaders headers, PostApplicationDto.Request request) throws BaseException {
        User user;
        try {
            user = this.tokenService.getUserFromHttpHeaders(headers);
        } catch (BaseException e) {
            throw e;
        }
        Optional<Application> applicationOpt = applicationRepository.findByUserIdAndRecruitmentId(user.getId(), request.getRecruitmentId());
        if (applicationOpt.isPresent()) {
            throw new BaseException(DUPLICATED_APPLICATION);
        }
        Application application = Application.builder()
            .userId(user.getId())
            .recruitmentId(request.getRecruitmentId())
            .appliedAt(LocalDateTime.now())
            .updatedAt(LocalDateTime.now())
            .status(ApplicationStatus.PENDING.value)
            .build();
        try {
            applicationRepository.save(application);
        } catch (Exception e) {
            throw new BaseException(UNKNOWN_ERROR);
        }
    }

    @Override
    public void changeStatus(HttpHeaders headers, ChangeApplicationStatusDto.Request request) throws BaseException{
        User user;
        try {
            user = this.tokenService.getUserFromHttpHeaders(headers);
        } catch (BaseException e) {
            throw e;
        }
        Optional<Application> applicationOpt = this.applicationRepository.findById(request.getApplicationId());
        if (applicationOpt.isEmpty()){
            throw new BaseException(NO_APPLICATION_FOUND);
        }
        Application application = applicationOpt.get();
        Optional<Recruitment> recruitmentOpt = this.recruitmentRepository.findById(application.getRecruitmentId());
        if (recruitmentOpt.isEmpty()) {
            throw new BaseException(RECRUITMENT_NOT_FOUND);
        }
        Recruitment recruitment = recruitmentOpt.get();
        Optional<Company> companyOpt = this.companyRepository.findById(recruitment.getCompanyId());
        if (companyOpt.isEmpty()) {
            throw new BaseException(COMPANY_NOT_FOUND);
        }
        Company company = companyOpt.get();
        boolean isRecruiter = company.getUserId() == user.getId();
        System.out.println("---> " + company.getUserId() + ", " + user.getId());
        if (!ApplicationStatus.allValues().contains(request.getStatus())) {
            throw new BaseException(INVALID_APPLICATION_STATUS);
        }
        if (isRecruiter) {
            application.setStatus(request.getStatus());
        } else {
            throw new BaseException(NO_AUTHORITY);
        }
        try {
            this.applicationRepository.save(application);
        } catch (Exception e) {
            throw new BaseException(UNKNOWN_ERROR);
        }

    }

    @Override
    public void deleteApplication(HttpHeaders headers, DeleteApplicationDto.Request request) throws BaseException{
        User user;
        try {
            user = this.tokenService.getUserFromHttpHeaders(headers);
        } catch (BaseException e) {
            throw e;
        }
        Optional<Application> applicationOpt = this.applicationRepository.findById(request.getApplicationId());
        if(applicationOpt.isEmpty()){
            throw new BaseException(NO_APPLICATION_FOUND);
        }
        if (applicationOpt.get().getUserId() != user.getId()) {
            throw new BaseException(NO_AUTHORITY);
        }
        Application application = applicationOpt.get();

        if (application.getUserId() != user.getId()) {
            throw new BaseException(NO_AUTHORITY);
        }
        try {
            this.applicationRepository.delete(application);
        } catch (Exception e) {
            throw new BaseException(UNKNOWN_ERROR);
        }
    }
    
}
