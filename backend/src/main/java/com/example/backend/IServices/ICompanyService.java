package com.example.backend.IServices;

import java.util.List;

import com.example.backend.EntityDto.CompanyDto;
import com.example.backend.EntityDto.ContactDto;

public interface ICompanyService {

    CompanyDto createCompany(CompanyDto companyDto);

    CompanyDto getCompanyById(Long companyId);

    List<CompanyDto> getAllCompanys();

    void deleteCompany(Long companyId);

    CompanyDto addContactToCompany(Long companyId , ContactDto contactDto);

    CompanyDto updateCompany(Long companyId , CompanyDto updatedCmCompanyDto);
}

