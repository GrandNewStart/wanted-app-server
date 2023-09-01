package com.rcplus.wanted.services.impls;

import static com.rcplus.wanted.configs.BaseResponseStatus.*;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rcplus.wanted.configs.BaseException;
import com.rcplus.wanted.models.Application;
import com.rcplus.wanted.repositories.ApplicationRepository;
import com.rcplus.wanted.services.ApplicationService;

@Service
public class ApplicationServiceImpl implements ApplicationService{
    @Autowired
    private ApplicationRepository applicationRepository;

    @Override
    public boolean checkApplicationExists(Long userId, Long recruitmentId) {
        Optional<Application> application = applicationRepository.findByUserIdAndRecruitmentId(userId, recruitmentId);
        return application.isPresent();
    }

    @Override
    public List<Application> getApplications(Long userId){
        return applicationRepository.findByUserId(userId);
    }

    @Override
    public void deleteApplication(Long applicationId) throws BaseException{
        Optional<Application> applicationCheck = applicationRepository.findById(applicationId);

        if(applicationCheck.isEmpty()){
            throw new BaseException(NO_EXISTS_APPLICATION);
        }

        Application application = applicationCheck.get();
        applicationRepository.delete(application);

    }

    @Override
    public void changeStatus(Long applicationId, String status) throws BaseException{
        Optional<Application> applicationCheck = applicationRepository.findById(applicationId);

        if(applicationCheck.isEmpty()){
            throw new BaseException(NO_EXISTS_APPLICATION);
        }

        Application application = applicationCheck.get();
        application.setStatus(status);
        applicationRepository.save(application);

    }
    

}
