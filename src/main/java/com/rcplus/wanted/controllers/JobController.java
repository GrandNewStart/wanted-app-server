package com.rcplus.wanted.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rcplus.wanted.configs.BaseResponse;
import com.rcplus.wanted.models.JobField;
import com.rcplus.wanted.models.JobSpecialty;

@RestController
public class JobController {

    @GetMapping("/job-fields")
    public BaseResponse getJobFields() {
        List<String> data = new ArrayList<>();
        for (JobField value : JobField.values()) {
            data.add(value.getName());
        }
        return BaseResponse.builder()
            .result("success")
            .message("직업군 목록을 조회하였습니다.")
            .data(data)
            .build();
    }

    @GetMapping("/specialties")
    public BaseResponse getSpecialties(@RequestParam(name = "field") String field) {
        if (field == null) {
            return BaseResponse.builder()
                .result("failure")
                .message("'field' 쿼리가 없습니다.")
                .build();
        }
        JobField jobField = null;
        for (JobField value : JobField.values()) {
            if (value.getName().equals(field)) {
                jobField = value;
                break;
            }
        }
        if (jobField == null) {
            return BaseResponse.builder()
                .result("failure")
                .message("'"+ field +"' 직무가 존재하지 않습니다.")
                .build();
        }
        List<String> data = new ArrayList<>();
        for (JobSpecialty value : JobSpecialty.values()) {
            if (value.getField() == jobField) {
                data.add(value.getName());
            }
        }
        return BaseResponse.builder()
            .result("success")
            .message("직업군 목록을 조회하였습니다.")
            .data(data)
            .build();
    }
    
}
