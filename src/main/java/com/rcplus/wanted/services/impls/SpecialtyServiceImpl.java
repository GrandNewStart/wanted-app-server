package com.rcplus.wanted.services.impls;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.rcplus.wanted.models.JobField;
import com.rcplus.wanted.models.JobSpecialty;
import com.rcplus.wanted.services.SpecialtyService;

@Service
public class SpecialtyServiceImpl implements SpecialtyService {

    @Override
    public List<JobField> getAllJobFields() {
        List<JobField> result = new ArrayList<>();
        for (JobField value : JobField.values()) {
            result.add(value);
        }
        return result;
    }

    @Override
    public List<JobSpecialty> getAllSpecialties() {
        List<JobSpecialty> result = new ArrayList<>();
        for (JobSpecialty value : JobSpecialty.values()) {
            result.add(value);
        }
        return result;
    }

    @Override
    public List<JobSpecialty> findAllSpecialtiesByJobField(JobField field) {
        List<JobSpecialty> result = new ArrayList<>();
        for (JobSpecialty value : JobSpecialty.values()) {
            if (value.getField() == field) {
                result.add(value);
            }
        }
        return result;
    }

    @Override
    public JobField findJobFieldByIndex(int index) {
        List<JobField> fields = new ArrayList<>();
        for (JobField value : JobField.values()) {
            fields.add(value);
        }
        return fields.get(index);
    }

    @Override
    public JobSpecialty findSpecialtyByIndex(JobField field, int index) {
        List<JobSpecialty> specialties = new ArrayList<>();
        for (JobSpecialty value : JobSpecialty.values()) {
            if (value.getField() == field) {
                specialties.add(value);
            }
        }
        return specialties.get(index);
    }
    
}
