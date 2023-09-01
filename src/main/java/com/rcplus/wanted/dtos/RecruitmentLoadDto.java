package com.rcplus.wanted.dtos;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.rcplus.wanted.models.Recruitment;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

public class RecruitmentLoadDto {
    
    @Getter
    @Setter
    public static class Request{
        private Long recruitmentId;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    public static class Response{
        @JsonProperty("company_name")
        private String companyName;

        @JsonProperty("image_url")
        private String imageUrl;

        @JsonProperty("title")
        private String title;

        @JsonProperty("description")
        private String description;

        @JsonProperty("upload_date")
        private	LocalDateTime uploadDate;

        @JsonProperty("due_date")
        private LocalDate dueDate;

        @JsonProperty("address")
        private String address;

        @JsonProperty("like")
        private int like;

        @JsonProperty("job_specialities")
        private List<String> jobSpecialities;

        public static Response from(Recruitment recruitment,String companyName, String imageUrl, int like) {
            return new Response(
                companyName,
                imageUrl,
                recruitment.getTitle(),
                recruitment.getDescription(),
                recruitment.getUploadDate(),
                recruitment.getDueDate(),
                recruitment.getAddress(),
                like,
                recruitment.getJobSpecialities()
            );
        }
    }

}
