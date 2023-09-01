package com.rcplus.wanted.dtos;




import java.util.List;

import com.rcplus.wanted.models.Application;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

public class AppliedApplicationsDto {

    @Getter
    @Setter
    @Builder
    public static class Response {
        private List<Application> applications;

    }

}