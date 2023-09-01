package com.rcplus.wanted.services;

import org.springframework.stereotype.Service;

import com.rcplus.wanted.configs.BaseException;
import com.rcplus.wanted.models.Recruitment;

@Service
public interface RecruitmentService {
    public Recruitment getRecruitmentById(Long recruitmentId);
    public void applyRecruitmentById(Long userId, Long recruitmentId) throws BaseException;
    public int getLike(Long recruitmentId);
    public void addLike(Long recruitmentId, Long userId) throws BaseException;
    public void deleteLike(Long recruitmentId, Long userId) throws BaseException;

}









    // @Autowired
    // private RecruitmentRepository recruitmentRepository;
    // private UserRepository userRepository;
    // private CompanyRepository companyRepository;
    // private LikeRepository likeRepository;
    // private ApplicationRepository applicationRepository;

    // public RecruitmentLoadDto.Response getRecruitmentById(Long recruitmentId){
        
        
    //     Recruitment recruitment = recruitmentRepository.findById(recruitmentId).orElseThrow();
    //     Company company = companyRepository.findById(recruitment.getCompanyId()).orElseThrow();
    //     User user = userRepository.findById(company.getUserId()).orElseThrow();
    //     long likeCnt = likeRepository.countByCompanyId(recruitmentId);

    //     return RecruitmentLoadDto.Response.builder()
    //         .title(recruitment.getTitle())
    //         .description(recruitment.getDescription())
    //         .uploadDate(recruitment.getUploadDate())
    //         .dueDate(recruitment.getDueDate())
    //         .address(recruitment.getAddress())
    //         .companyName(company.getName())
    //         .imageUrl(company.getImage())
    //         .jobSpecialities(recruitment.getJobSpecialities())
    //         .like(likeCnt)
    //         .build();
    // }

