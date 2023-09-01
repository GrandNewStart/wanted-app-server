package com.rcplus.wanted.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "companies")
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Company {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    @JsonProperty("user_id")
    private Long userId;

    private String name;

    @JsonProperty("business_reg_code")
    private String businessRegCode;

    private String nation;
    
    private String region;

    private String image;

    private String industry;

    @JsonProperty("employee_count")
    private String employeeCount;

    @JsonProperty("marketing_email")
    private String marketingEmail;

    @JsonProperty("marketing_push")
    private String marketingPush;
    
    @JsonProperty("marketing_sms")
    private String marketingSms;

}
