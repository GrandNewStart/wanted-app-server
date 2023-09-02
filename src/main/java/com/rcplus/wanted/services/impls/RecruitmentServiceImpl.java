package com.rcplus.wanted.services.impls;

import static com.rcplus.wanted.configs.BaseResponseStatus.*;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import com.rcplus.wanted.configs.BaseException;
import com.rcplus.wanted.configs.jwt.TokenService;
import com.rcplus.wanted.dtos.ApplicationDto;
import com.rcplus.wanted.dtos.DeleteRecruitmentDto;
import com.rcplus.wanted.dtos.PostRecruitmentDto;
import com.rcplus.wanted.dtos.RecruitmentDto;
import com.rcplus.wanted.models.Application;
import com.rcplus.wanted.models.Company;
import com.rcplus.wanted.models.Like;
import com.rcplus.wanted.models.Recruitment;
import com.rcplus.wanted.models.User;
import com.rcplus.wanted.repositories.ApplicationRepository;
import com.rcplus.wanted.repositories.CompanyRepository;
import com.rcplus.wanted.repositories.LikeRepository;
import com.rcplus.wanted.repositories.RecruitmentRepository;
import com.rcplus.wanted.services.ApplicationService;
import com.rcplus.wanted.services.RecruitmentService;

@Service
public class RecruitmentServiceImpl implements RecruitmentService{

    @Autowired
    private TokenService tokenService;

    @Autowired
    private RecruitmentRepository recruitmentRepository;

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private ApplicationService applicationService;

    @Autowired
    private ApplicationRepository applicationRepository;

    @Autowired
    private LikeRepository likeRepository;

    @Override
    public List<RecruitmentDto> getRecruitments() {
        return this.recruitmentRepository.findAll()
            .stream()
            .map(e->{
                Optional<Company> companyOpt = this.companyRepository.findById(e.getCompanyId());
                if (companyOpt.isEmpty()) {
                    return null;
                }
                int likeCount = this.likeRepository.countByRecruitmentId(e.getId());
                return RecruitmentDto.from(e, companyOpt.get(), likeCount);
            })
            .filter(e->e != null)
            .toList();
    }

    @Override
    public RecruitmentDto getRecruitment(Long recruitmentId) throws BaseException {
        Optional<Recruitment> recruitmentOpt = this.recruitmentRepository.findById(recruitmentId);
        if (recruitmentOpt.isEmpty()) {
            throw new BaseException(RECRUITMENT_NOT_FOUND);
        }
        Optional<Company> companyOpt = this.companyRepository.findById(recruitmentOpt.get().getCompanyId());
        if (companyOpt.isEmpty()) {
            throw new BaseException(COMPANY_NOT_FOUND);
        }
        int likeCount = this.likeRepository.countByRecruitmentId(recruitmentId);
        return RecruitmentDto.from(
            recruitmentOpt.get(),
            companyOpt.get(),
            likeCount
        );
    }

    @Override
    public PostRecruitmentDto.Response addRecruitment(HttpHeaders headers, PostRecruitmentDto.Request request) throws BaseException {
        User user;
        try {
            user = this.tokenService.getUserFromHttpHeaders(headers);
        } catch (BaseException e) {
            throw e;
        }
        
        Optional<Company> companyOpt = this.companyRepository.findByUserId(user.getId());
        if (companyOpt.isEmpty()) {
            throw new BaseException(COMPANY_NOT_FOUND);
        }
        Company company = companyOpt.get();
        if (company.getId() != request.getCompanyId()) {
            throw new BaseException(NO_AUTHORITY);
        }
        try {
            Recruitment newItem = this.recruitmentRepository.save(request.toRecruitment());
            return PostRecruitmentDto.Response.from(newItem);
        } catch(Exception e) {
            throw new BaseException(UNKNOWN_ERROR);
        }
    }

    @Override
    public void deleteRecruitment(HttpHeaders headers, DeleteRecruitmentDto.Request request) throws BaseException {
        User user;
        try {
            user = this.tokenService.getUserFromHttpHeaders(headers);
        } catch (BaseException e) {
            throw e;
        }
        Optional<Recruitment> recruitmentOpt = this.recruitmentRepository.findById(request.getRecruitmentId());
        if (recruitmentOpt.isEmpty()) {
            throw new BaseException(RECRUITMENT_NOT_FOUND);
        }
        Recruitment recruitment = recruitmentOpt.get();
        Optional<Company> companyOpt = this.companyRepository.findByUserId(user.getId());
        if (companyOpt.isEmpty()) {
            throw new BaseException(COMPANY_NOT_FOUND);
        }
        Company company = companyOpt.get();
        if (recruitment.getCompanyId() != company.getId()) {
            throw new BaseException(NO_AUTHORITY);
        }
        try {
            this.recruitmentRepository.delete(recruitment);
        } catch (Exception e) {
            throw new BaseException(UNKNOWN_ERROR);
        }
    }

    @Override
    public int getLikeCount(Long recruitmentId){
        int likeCount = this.likeRepository.countByRecruitmentId(recruitmentId);
        return likeCount;
    }
    @Override
    public void addLike(HttpHeaders headers, Long recruitmentId) throws BaseException{
        User user;
        try {
            user = this.tokenService.getUserFromHttpHeaders(headers);
        } catch (BaseException e) {
            throw e;
        }
        Optional<Like> likeOpt = this.likeRepository.findByUserIdAndRecruitmentId(user.getId(), recruitmentId);
        if(likeOpt.isPresent()){
            throw new BaseException(DUPLICATED_LIKE);
        }
        Like like = Like.builder()
            .userId(user.getId())
            .recruitmentId(recruitmentId)
            .build();
        try {
            this.likeRepository.save(like);
        } catch (Exception e) {
            throw new BaseException(UNKNOWN_ERROR);
        }
    }
    @Override
    public void deleteLike(HttpHeaders headers, Long recruitmentId) throws BaseException{
        User user;
        try {
            user = this.tokenService.getUserFromHttpHeaders(headers);
        } catch (BaseException e) {
            throw e;
        }
        Optional<Like> likeOpt = this.likeRepository.findByUserIdAndRecruitmentId(user.getId(), recruitmentId);
        if(likeOpt.isEmpty()){
            throw new BaseException(NO_LIKE_FOUND);
        }
        try {
            likeRepository.delete(likeOpt.get());
        } catch (Exception e) {
            throw new BaseException(UNKNOWN_ERROR);
        }
    }



}
