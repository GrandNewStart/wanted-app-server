package com.rcplus.wanted.services.impls;

import java.util.Base64;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import com.rcplus.wanted.configs.BaseException;
import com.rcplus.wanted.dtos.DeleteCompanyDto;
import com.rcplus.wanted.dtos.GetCompanyInfoDto;
import com.rcplus.wanted.dtos.RegisterCompanyDto;
import com.rcplus.wanted.models.Company;
import com.rcplus.wanted.models.User;
import com.rcplus.wanted.repositories.CompanyRepository;
import com.rcplus.wanted.repositories.UserRepository;
import com.rcplus.wanted.services.CompanyService;

import static com.rcplus.wanted.configs.BaseResponseStatus.*;

@Service
public class CompanyServiceImpl implements CompanyService {

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public RegisterCompanyDto.Response registerCompany(HttpHeaders headers, RegisterCompanyDto.Request request) throws BaseException {
        User user;
        try {
            String[] credentials = this.getUserCredentials(headers);
            user = this.getUserFromCredentials(credentials);
        } catch (BaseException e) {
            throw e;
        }
        Optional<Company> existingCompany = this.companyRepository.findByBusinessRegCode(request.getBusinessRegCode());
        if (existingCompany.isPresent()) {
            throw new BaseException(DUPLICATE_BUSINESS_REG_CODE);
        }
        Long userId = user.getId();
        Company newCompany = this.companyRepository.save(request.toCompany(userId));
        return new RegisterCompanyDto.Response(newCompany.getId());
    }

    @Override
    public GetCompanyInfoDto.Response getCompany(HttpHeaders headers, Long companyId) throws BaseException {
        User user;
        try {
            String[] credentials = this.getUserCredentials(headers);
            user = this.getUserFromCredentials(credentials);
        } catch (BaseException e) {
            throw e;
        }
        Optional<Company> company = this.companyRepository.findById(companyId);
        if (company.isEmpty()) {
            throw new BaseException(COMPANY_NOT_FOUND);
        }
        if (company.get().getUserId() != user.getId()) {
            throw new BaseException(INVALID_USER_JWT);
        }
        return GetCompanyInfoDto.Response.from(company.get());
    }

    @Override
    public void deleteCompany(HttpHeaders headers, DeleteCompanyDto.Request request) throws BaseException {
        User user;
        try {
            String[] credentials = this.getUserCredentials(headers);
            user = this.getUserFromCredentials(credentials);
        } catch (BaseException e) {
            throw e;
        }
        Optional<Company> company = this.companyRepository.findById(request.getCompanyId());
        if (company.isEmpty()) {
            throw new BaseException(COMPANY_NOT_FOUND);
        }
        if (company.get().getId() != request.getCompanyId()) {
            throw new BaseException(COMPANY_NOT_FOUND);
        }
        if (company.get().getUserId() != user.getId()) {
            throw new BaseException(INVALID_USER_JWT);
        }
        if (company.get().getUserId() != request.getUserId()) {
            throw new BaseException(INVALID_USER_JWT);
        }
        if (!user.getPassword().equals(request.getPassword())) {
            throw new BaseException(INVALID_USER_JWT);
        }
        this.companyRepository.delete(company.get());
    }

    private String[] getUserCredentials(HttpHeaders headers) throws BaseException {
        List<String> authHeaders = headers.get("authorization");
        if (authHeaders == null) {
            throw new BaseException(INVALID_JWT);
        }
        String auth = authHeaders.get(0);
        if (auth == null) {
            throw new BaseException(INVALID_JWT);
        }
        String[] auths = auth.split(" ");
        if (auths.length != 2) {
            throw new BaseException(INVALID_JWT);
        }
        if (!auths[0].equals("Basic")) {
            throw new BaseException(INVALID_JWT);
        }
        String credentialString = new String(Base64.getDecoder().decode(auths[1]));
        String[] credentials = credentialString.split(":");
        if (credentials.length != 2) {
            throw new BaseException(INVALID_JWT);
        }
        return credentials;
    }

    private User getUserFromCredentials(String[] credentials) throws BaseException {
        String email = credentials[0];
        String password = credentials[1];
        Optional<User> user = this.userRepository.findByEmail(email);
        if (user.isEmpty()) {
            throw new BaseException(INVALID_JWT);
        }
        if (!user.get().getPassword().equals(password)) {
            throw new BaseException(INVALID_JWT);
        }
        return user.get();
    }
    
}
