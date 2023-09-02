package com.rcplus.wanted.dtos;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.rcplus.wanted.models.Company;
import com.rcplus.wanted.models.Recruitment;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class RecruitmentDto {

        private Long id;

        @JsonProperty("company_id")
        private Long companyId;

        @JsonProperty("company_name")
        private String companyName;

        @JsonProperty("company_image")
        private String companyImage;

        private String image;

        @JsonProperty("title")
        private String title;

        @JsonProperty("description")
        private String description;

        @JsonProperty("upload_date")
        private	String uploadDate;

        @JsonProperty("update_date")
        private	String updateDate;

        @JsonProperty("due_date")
        private String dueDate;

        @JsonProperty("address")
        private String address;

        @JsonProperty("like_count")
        private int likeCount;

        @JsonProperty("job_field")
        private String jobField;

        @JsonProperty("job_specialities")
        private List<String> jobSpecialities;

        public static RecruitmentDto from(Recruitment recruitment, Company company, int likeCount) {
            return new RecruitmentDto(
                recruitment.getId(),
                recruitment.getCompanyId(),
                company.getName(),
                company.getImage(),
                recruitment.getImage(),
                recruitment.getTitle(),
                recruitment.getDescription(),
                recruitment.getUploadDate().toString(),
                recruitment.getUpdateDate().toString(),
                recruitment.getDueDate().toString(),
                recruitment.getAddress(),
                likeCount,
                recruitment.getJobField(),
                recruitment.getJobSpecialities()
            );
        }

}
