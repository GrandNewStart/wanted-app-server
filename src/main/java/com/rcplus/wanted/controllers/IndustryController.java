package com.rcplus.wanted.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rcplus.wanted.configs.BaseResponse;
import com.rcplus.wanted.models.Industry;

@RestController
public class IndustryController {

    @GetMapping("/industries")
    public BaseResponse getIndustries() {
        List<String> data = new ArrayList<>();
        for (Industry value : Industry.values()) {
            data.add(value.getName());
        };
        return BaseResponse.builder()
            .result("success")
            .message("산업군 목록을 조회하였습니다.")
            .data(data)
            .build();
    }
    
}
