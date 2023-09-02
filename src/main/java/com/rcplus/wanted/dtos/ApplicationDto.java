package com.rcplus.wanted.dtos;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.rcplus.wanted.models.Application;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class ApplicationDto {

    private Long id;

    @JsonProperty("user_id")
    private Long userId;

    @JsonProperty("recruitment_id")
    private Long recruitmentId;

    @JsonProperty("applied_at")
    private String appliedAt;

    @JsonProperty("updated_at")
    private String updatedAt;

    private String status;

    public static ApplicationDto from(Application application) {
        return ApplicationDto.builder()
            .id(application.getId())
            .userId(application.getUserId())
            .recruitmentId(application.getRecruitmentId())
            .appliedAt(application.getAppliedAt().toString())
            .updatedAt(application.getUpdatedAt().toString())
            .status(application.getStatus())
            .build();
    }

}
