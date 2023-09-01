package com.rcplus.wanted.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rcplus.wanted.configs.BaseResponse;
import com.rcplus.wanted.models.Nation;
import com.rcplus.wanted.models.Region;

import static com.rcplus.wanted.configs.BaseResponseStatus.*;

@RestController
public class NationController {
    
    @GetMapping("/nations")
    public ResponseEntity<BaseResponse> getNations() {
        List<String> data = new ArrayList<>();
        for (Nation value : Nation.values()) {
            data.add(value.getName());
        }
        return new ResponseEntity<>(new BaseResponse(data), HttpStatus.OK);
    }

    @GetMapping("/regions")
    public ResponseEntity<BaseResponse> getRegions(@RequestParam(name = "nation") String nation) {
        Nation _nation = null;
        for (Nation value : Nation.values()) {
            if (value.getName().equals(nation)) {
                _nation = value;
                break;
            }
        }
        if (_nation == null) {
            return new ResponseEntity<>(new BaseResponse(REQUEST_ERROR), REQUEST_ERROR.getStatus());
        }
        List<String> data = new ArrayList<>();
        for (Region value : Region.values()) {
            if (value.getNation().equals(_nation)) {
                data.add(value.getName());
            }
        }
        return new ResponseEntity<>(new BaseResponse(data), HttpStatus.OK);
    }

}
