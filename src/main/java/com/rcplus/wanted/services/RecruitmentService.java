package com.rcplus.wanted.services;

import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import com.rcplus.wanted.configs.BaseException;
import com.rcplus.wanted.dtos.DeleteRecruitmentDto;
import com.rcplus.wanted.dtos.PostRecruitmentDto;
import com.rcplus.wanted.dtos.RecruitmentDto;

@Service
public interface RecruitmentService {
    public List<RecruitmentDto> getRecruitments();
    public RecruitmentDto getRecruitment(Long recruitmentId) throws BaseException;
    public PostRecruitmentDto.Response addRecruitment(HttpHeaders headers, PostRecruitmentDto.Request request) throws BaseException;
    public void deleteRecruitment(HttpHeaders headers, DeleteRecruitmentDto.Request request) throws BaseException;
    public int getLikeCount(Long recruitmentId);
    public void addLike(HttpHeaders headers, Long recruitmentId) throws BaseException;
    public void deleteLike(HttpHeaders headers, Long recruitmentId) throws BaseException;
}