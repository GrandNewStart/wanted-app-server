package com.rcplus.wanted.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rcplus.wanted.configs.BaseResponse;
import com.rcplus.wanted.models.Industry;

@RestController
public class IndustryController {

    @GetMapping("/industries")
    public ResponseEntity<BaseResponse> getIndustries() {
        List<String> data = new ArrayList<>();
        for (Industry value : Industry.values()) {
            data.add(value.getName());
        };
        return new ResponseEntity<>(new BaseResponse(data), HttpStatus.OK);
    }
    
}
