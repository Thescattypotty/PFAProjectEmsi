package com.example.backend.Mapper;

import java.util.stream.Collectors;

import com.example.backend.Entity.Company;
import com.example.backend.EntityDto.CompanyDto;

public class CompanyMapper {
    public static CompanyDto mapToCompanyDto(Company company)
    {
        if(company == null)
        {
            return null;
        }
        return new CompanyDto(
            company.getId(),
            company.getCompanyName(),
            company.getCompanyLogo(),
            company.getCompanySize(),
            company.getTotalIncome(),
            company.getIndustry(),
            company.getBusinessType(),
            company.getCountry(),
            company.getWebsite(),
            ContactMapper.mapToContactDto(company.getSalesOwner()),
            company.getContacts().stream().map(ContactMapper::mapToContactDto).collect(Collectors.toList()),
            company.getCreatedAt(),
            company.getCreatedBy()
        );
    }
    public static Company mapToCompany(CompanyDto companyDto)
    {
        if (companyDto == null) {
            return null;
        }
        return new Company(
            companyDto.getCompanyName(),
            companyDto.getCompanyLogo(),
            companyDto.getCompanySize(),
            companyDto.getTotalIncome(),
            companyDto.getIndustry(),
            companyDto.getBusinessType(),
            companyDto.getCountry(),
            companyDto.getWebsite(),
            ContactMapper.mapToContact(companyDto.getSalesOwner()),
            companyDto.getContacts().stream().map(
                ContactMapper::mapToContact
            ).collect(Collectors.toList())
        );
    }

}
