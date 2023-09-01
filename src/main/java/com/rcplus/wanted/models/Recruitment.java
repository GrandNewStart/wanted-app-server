package com.rcplus.wanted.models;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity(name = "recruitment")
@Getter
@Setter
public class Recruitment{

    @Id
    @GeneratedValue
    @Column(updatable = false)
    private Long id;

    @Column(name = "company_id")
    private Long companyId;

    @Column(name = "job_field")
    private String jobField;

    @Column(name = "job_specialities")
    private List<String> jobSpecialities;

    private String title;

    private String description;

    @Column(name = "upload_date")
    private LocalDateTime uploadDate;

    @Column(name = "update_date")
    private LocalDateTime updateDate;

    @Column(name = "due_date")
    private LocalDate dueDate;

    private String address;






    
}
