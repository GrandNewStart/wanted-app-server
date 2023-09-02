package com.rcplus.wanted.dtos;

import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ApplicationListDto {

    private List<ApplicationDto> applications;

}