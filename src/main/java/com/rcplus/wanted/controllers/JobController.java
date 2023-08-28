package com.rcplus.wanted.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rcplus.wanted.configs.BaseResponse;
import com.rcplus.wanted.models.JobField;
import com.rcplus.wanted.models.JobSpecialty;

import static com.rcplus.wanted.configs.BaseResponseStatus.*;

@RestController
public class JobController {

    @GetMapping("/job-fields")
    public BaseResponse getJobFields() {
        List<String> data = new ArrayList<>();
        for (JobField value : JobField.values()) {
            data.add(value.getName());
        }
        return new BaseResponse(data);
    }

    @GetMapping("/specialties")
    public BaseResponse getSpecialties(@RequestParam(name = "field") String field) {
        if (field == null) {
            return new BaseResponse(REQUEST_ERROR);
        }
        JobField jobField = null;
        for (JobField value : JobField.values()) {
            if (value.getName().equals(field)) {
                jobField = value;
                break;
            }
        }
        if (jobField == null) {
            return new BaseResponse(REQUEST_ERROR);
        }
        List<String> data = new ArrayList<>();
        for (JobSpecialty value : JobSpecialty.values()) {
            if (value.getField() == jobField) {
                data.add(value.getName());
            }
        }
        return new BaseResponse(data);
    }
    
}
