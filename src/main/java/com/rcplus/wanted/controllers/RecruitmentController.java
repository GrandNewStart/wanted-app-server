package com.rcplus.wanted.controllers;

import static com.rcplus.wanted.configs.BaseResponseStatus.*;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.rcplus.wanted.configs.BaseException;
import com.rcplus.wanted.configs.BaseResponse;
import com.rcplus.wanted.dtos.RecruitmentLoadDto;
import com.rcplus.wanted.models.Company;
import com.rcplus.wanted.models.Recruitment;
import com.rcplus.wanted.repositories.CompanyRepository;
import com.rcplus.wanted.services.RecruitmentService;


@RestController
public class RecruitmentController {
    @Autowired
    private RecruitmentService recruitmentService;

    @Autowired
    private CompanyRepository companyRepository;
    @GetMapping("/recruitments")
    public BaseResponse loadRecruitment(@RequestBody Long recruitmentId){

        Recruitment recruitment = recruitmentService.getRecruitmentById(recruitmentId);
        Long companyId = recruitment.getCompanyId();
        Optional<Company> companyCheck = companyRepository.findById(companyId);

        if(companyCheck.isEmpty()){
            return new BaseResponse(RESPONSE_ERROR);
        }

        Company company = companyCheck.get();
        String companyName =company.getName();
        String imageUrl = company.getImage();
        int like = recruitmentService.getLike(recruitmentId);

        RecruitmentLoadDto.Response data = RecruitmentLoadDto.Response.from(recruitment, companyName, imageUrl, like);
        return new BaseResponse(data);
    }

    @PostMapping("/recruitments")
    public BaseResponse applyRecruitment(@RequestHeader HttpHeaders headers, @RequestBody Long recruitmentId){
        try{
            //토큰으로 유저아이디 저장
            Long userId = 123l;
            //

            recruitmentService.applyRecruitmentById(userId, recruitmentId);
            return new BaseResponse(SUCCESS);
        }
        catch(BaseException e){
            return new BaseResponse(e.getStatus());
        }

        
    }


}
