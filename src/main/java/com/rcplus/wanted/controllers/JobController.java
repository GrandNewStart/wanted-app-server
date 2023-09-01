package com.rcplus.wanted.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rcplus.wanted.configs.BaseException;
import com.rcplus.wanted.configs.BaseResponse;
import com.rcplus.wanted.models.JobField;
import com.rcplus.wanted.services.SpecialtyService;

import static com.rcplus.wanted.configs.BaseResponseStatus.*;

@RestController
public class JobController {

    @Autowired
    private SpecialtyService specialtyService;

    @GetMapping("/job-fields")
    public ResponseEntity<BaseResponse> getJobFields() {
        List<String> data = this.specialtyService.getAllJobFields()
            .stream()
            .map(e->e.getName())
            .toList();
        return new ResponseEntity<BaseResponse>(new BaseResponse(data), HttpStatus.OK);
    }

    @GetMapping("/specialties")
    public ResponseEntity<BaseResponse> getSpecialties(@RequestParam(name = "field") String field) {
        JobField jobField = null;
        for (JobField value : this.specialtyService.getAllJobFields()) {
            if (value.getName().equals(field)) {
                jobField = value;
                break;
            }
        }
        if (jobField == null) {
            return new ResponseEntity<BaseResponse>(new BaseResponse(REQUEST_ERROR), REQUEST_ERROR.getStatus());
        }
        BaseResponse data = new BaseResponse(this.specialtyService.findAllSpecialtiesByJobField(jobField));
        return new ResponseEntity<BaseResponse>(data, HttpStatus.OK);
    }
    
}
