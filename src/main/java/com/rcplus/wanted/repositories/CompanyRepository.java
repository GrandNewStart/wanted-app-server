package com.rcplus.wanted.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rcplus.wanted.models.Company;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {
    
    public Optional<Company> findByBusinessRegCode(String businessRegCode);
    public Optional<Company> findByUserId(Long userId);

}
