package com.rcplus.wanted.controllers;

import static com.rcplus.wanted.configs.BaseResponseStatus.*;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rcplus.wanted.configs.BaseException;
import com.rcplus.wanted.configs.BaseResponse;
import com.rcplus.wanted.dtos.CountRecruitmentLikeDto;
import com.rcplus.wanted.dtos.DeleteRecruitmentDto;
import com.rcplus.wanted.dtos.PostRecruitmentDto;
import com.rcplus.wanted.dtos.RecruitmentDto;
import com.rcplus.wanted.services.RecruitmentService;

@RestController
public class RecruitmentController {

    @Autowired
    private RecruitmentService recruitmentService;

    @GetMapping("/recruitments/all")
    public ResponseEntity<BaseResponse> getAllRecruitments() {
        List<RecruitmentDto> data = this.recruitmentService.getRecruitments();
        BaseResponse response = new BaseResponse(data);
        return new ResponseEntity<BaseResponse>(response, HttpStatus.OK);
    }

    @GetMapping("/recruitments")
    public ResponseEntity<BaseResponse> getRecruitments(@RequestParam Long id){
        try {
            RecruitmentDto data = this.recruitmentService.getRecruitment(id);
            BaseResponse response = new BaseResponse(data);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (BaseException e) {
            BaseResponse response = new BaseResponse(e.getStatus());
            return new ResponseEntity<>(response, e.getStatus().getStatus());
        }
    }

    @PostMapping("/recruitments")
    public ResponseEntity<BaseResponse> postRecruitment(@RequestHeader HttpHeaders headers, @RequestBody PostRecruitmentDto.Request request) {
        try {
            PostRecruitmentDto.Response data = this.recruitmentService.addRecruitment(headers, request);
            BaseResponse response = new BaseResponse(data);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (BaseException e) {
            BaseResponse response = new BaseResponse(e.getStatus());
            return new ResponseEntity<>(response, e.getStatus().getStatus());
        }
    }

    @DeleteMapping("/recruitments")
    public ResponseEntity<BaseResponse> deleteRecruitment(@RequestHeader HttpHeaders headers, @RequestBody DeleteRecruitmentDto.Request request) {
        try {
            this.recruitmentService.deleteRecruitment(headers, request);
            BaseResponse response = new BaseResponse(SUCCESS);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (BaseException e) {
            BaseResponse response = new BaseResponse(e.getStatus());
            return new ResponseEntity<>(response, e.getStatus().getStatus());
        }
    }

    @GetMapping("/recruitments/likes")
    public ResponseEntity<BaseResponse> getLike(@RequestParam Long id){
        int likeCount = this.recruitmentService.getLikeCount(id);
        CountRecruitmentLikeDto.Response data = CountRecruitmentLikeDto.Response.builder().likeCount(likeCount).build();
        BaseResponse response = new BaseResponse(data);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/recruitments/likes")
    public ResponseEntity<BaseResponse> addLike(@RequestHeader HttpHeaders headers, @RequestParam Long id){
        try{
            this.recruitmentService.addLike(headers, id);
            int likeCount = this.recruitmentService.getLikeCount(id);
            CountRecruitmentLikeDto.Response data = CountRecruitmentLikeDto.Response.builder().likeCount(likeCount).build();
            BaseResponse response = new BaseResponse(data);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        catch(BaseException e){
            BaseResponse response = new BaseResponse(e.getStatus());
            return new ResponseEntity<>(response, e.getStatus().getStatus());
        }
    }

    @DeleteMapping("/recruitments/likes")
    public ResponseEntity<BaseResponse> deleteLike(@RequestHeader HttpHeaders headers, @RequestParam Long id){
        try{
            this.recruitmentService.deleteLike(headers, id);
            int likeCount = this.recruitmentService.getLikeCount(id);
            CountRecruitmentLikeDto.Response data = CountRecruitmentLikeDto.Response.builder().likeCount(likeCount).build();
            BaseResponse response = new BaseResponse(data);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        catch(BaseException e){
            BaseResponse response = new BaseResponse(e.getStatus());
            return new ResponseEntity<>(response, e.getStatus().getStatus());
        }
    }

}
