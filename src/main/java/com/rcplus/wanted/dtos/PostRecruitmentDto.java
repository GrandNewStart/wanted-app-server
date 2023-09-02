package com.rcplus.wanted.dtos;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.rcplus.wanted.models.Recruitment;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

public class PostRecruitmentDto {
    
    @Getter
    @Setter
    public static class Request {
        @JsonProperty("company_id")
        private Long companyId;
        @JsonProperty("job_field")
        private String jobField;
        @JsonProperty("job_specialties")
        private List<String> jobSpecialties;
        private String title;
        private String description;
        private String address;
        @JsonProperty("due_date")
        private String dueDate;

        public Recruitment toRecruitment() {
            LocalDate dd = LocalDate.parse(dueDate);
            return Recruitment.builder()
                .companyId(companyId)
                .jobField(jobField)
                .jobSpecialities(jobSpecialties)
                .image(null)
                .title(title)
                .description(description)
                .uploadDate(LocalDateTime.now())
                .updateDate(LocalDateTime.now())
                .dueDate(dd)
                .address(address)
                .build();
        }
    }

    @Getter
    @Setter
    @Builder
    public static class Response {
        @JsonProperty("recruitment_id")
        private Long recruitmentId;
        @JsonProperty("company_id")
        private Long companyId;
        @JsonProperty("job_field")
        private String jobField;
        @JsonProperty("job_specialties")
        private List<String> jobSpecialties;
        private String title;
        private String description;
        private String address;
        @JsonProperty("due_date")
        private String dueDate;
        @JsonProperty("upload_date")
        private String uploadDate;
        @JsonProperty("update_date")
        private String updateDate;

        public static PostRecruitmentDto.Response from(Recruitment recruitment) {
            return Response.builder()
                .recruitmentId(recruitment.getId())
                .companyId(recruitment.getCompanyId())
                .jobField(recruitment.getJobField())
                .jobSpecialties(recruitment.getJobSpecialities())
                .title(recruitment.getTitle())
                .description(recruitment.getDescription())
                .address(recruitment.getAddress())
                .dueDate(recruitment.getDueDate().toString())
                .uploadDate(recruitment.getUploadDate().toString())
                .updateDate(recruitment.getUpdateDate().toString())
                .build();
        }
    }

}
