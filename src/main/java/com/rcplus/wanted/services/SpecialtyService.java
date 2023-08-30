package com.rcplus.wanted.services;

import java.util.List;

import com.rcplus.wanted.models.JobField;
import com.rcplus.wanted.models.JobSpecialty;

public interface SpecialtyService {
    public List<JobField> getAllJobFields();
    public List<JobSpecialty> getAllSpecialties();
    public List<JobSpecialty> findAllSpecialtiesByJobField(JobField field);
    public JobField findJobFieldByIndex(int index);
    public JobSpecialty findSpecialtyByIndex(JobField field, int index);
}
