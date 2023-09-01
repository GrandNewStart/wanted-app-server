package com.rcplus.wanted.services.impls;

import static com.rcplus.wanted.configs.BaseResponseStatus.*;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rcplus.wanted.configs.BaseException;
import com.rcplus.wanted.models.Application;
import com.rcplus.wanted.models.Like;
import com.rcplus.wanted.models.Recruitment;
import com.rcplus.wanted.repositories.ApplicationRepository;
import com.rcplus.wanted.repositories.LikeRepository;
import com.rcplus.wanted.repositories.RecruitmentRepository;
import com.rcplus.wanted.services.ApplicationService;
import com.rcplus.wanted.services.RecruitmentService;

@Service
public class RecruitmentServiceImpl implements RecruitmentService{
    @Autowired
    private RecruitmentRepository recruitmentRepository;

    @Autowired
    private ApplicationService applicationService;

    @Autowired
    private ApplicationRepository applicationRepository;

    @Autowired
    private LikeRepository likeRepository;

    @Override
    public Recruitment getRecruitmentById(Long recruitmentId){
        return this.recruitmentRepository.findById(recruitmentId).orElseThrow(()->new IllegalArgumentException("Unexpected recruitment"));
    }


    @Override
    public void applyRecruitmentById(Long userId, Long recruitmentId) throws BaseException{


        boolean exists = applicationService.checkApplicationExists(userId, recruitmentId);
        if(exists){
            throw new BaseException(DUPLICATED_APPLICATION);
        }

        Application application = new Application();
        application.setUserId(userId);
        application.setRecruitmentId(recruitmentId);
    

        applicationRepository.save(application);
    }


    @Override
    public int getLike(Long recruitmentId){
        int likeCount = likeRepository.countByRecruitmentId(recruitmentId);
        return likeCount;
    }
    @Override
    public void addLike(Long recruitmentId, Long userId) throws BaseException{
        Optional<Like> checkLike = likeRepository.findByUserIdAndRecruitmentId(userId, recruitmentId);
        if(checkLike.isPresent()){
            throw new BaseException(DUPLICATED_LIKE);
        }

        Like like = new Like();
        like.setUserId(userId);
        like.setRecruitmentId(recruitmentId);

        likeRepository.save(like);

    }
    @Override
    public void deleteLike(Long recruitmentId, Long userId) throws BaseException{
        Optional<Like> checkLike = likeRepository.findByUserIdAndRecruitmentId(userId, recruitmentId);
        if(checkLike.isEmpty()){
            throw new BaseException(NO_EXISTS_LIKE);
        }
        Like like = checkLike.get();
        likeRepository.delete(like);
    }



}
