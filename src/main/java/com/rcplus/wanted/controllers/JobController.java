package com.rcplus.wanted.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rcplus.wanted.configs.BaseResponse;
import com.rcplus.wanted.models.JobField;
import com.rcplus.wanted.services.SpecialtyService;

import static com.rcplus.wanted.configs.BaseResponseStatus.*;

@RestController
public class JobController {

    @Autowired
    private SpecialtyService specialtyService;

    @GetMapping("/job-fields")
    public BaseResponse getJobFields() {
        List<String> data = this.specialtyService.getAllJobFields()
            .stream()
            .map(e->e.getName())
            .toList();
        return new BaseResponse(data);
    }

    @GetMapping("/specialties")
    public BaseResponse getSpecialties(@RequestParam(name = "field") String field) {
        JobField jobField = null;
        for (JobField value : this.specialtyService.getAllJobFields()) {
            if (value.getName().equals(field)) {
                jobField = value;
                break;
            }
        }
        if (jobField == null) {
            return new BaseResponse(REQUEST_ERROR);
        }
        return new BaseResponse(this.specialtyService.findAllSpecialtiesByJobField(jobField));
    }
    
}
