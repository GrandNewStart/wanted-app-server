package com.rcplus.wanted.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rcplus.wanted.configs.BaseResponse;
import com.rcplus.wanted.models.Nation;
import com.rcplus.wanted.models.Region;

@RestController
public class NationController {
    
    @GetMapping("/nations")
    public BaseResponse getNations() {
        List<String> data = new ArrayList<>();
        for (Nation value : Nation.values()) {
            data.add(value.getName());
        }
        return BaseResponse.builder()
            .result("success")
            .message("국가 목록 조회에 성공하였습ㄴ다.")
            .data(data)
            .build();
    }

    @GetMapping("/regions")
    public BaseResponse getRegions(@RequestParam(name = "nation") String nation) {
        Nation _nation = null;
        for (Nation value : Nation.values()) {
            if (value.getName().equals(nation)) {
                _nation = value;
                break;
            }
        }
        if (_nation == null) {
            return BaseResponse.builder()
            .result("failure")
            .message("'" + nation + "'에 해당하는 국가를 찾을 수 없습니다.")
            .build();
        }
        List<String> data = new ArrayList<>();
        for (Region value : Region.values()) {
            if (value.getNation().equals(_nation)) {
                data.add(value.getName());
            }
        }
        return BaseResponse.builder()
            .result("success")
            .message("지역 목록 조회에 성공하였습ㄴ다.")
            .data(data)
            .build();
    }

}
