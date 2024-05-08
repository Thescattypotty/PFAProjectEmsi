package com.example.backend.MapToResponse;

import com.example.backend.Entity.Company;
import com.example.backend.Payload.Response.CompanyResponse;

public class CompanyMapperResponse {
    

    public static CompanyResponse MapToCompanyResponse(Company company)
    {
        return new CompanyResponse(
            company.getId(), 
            company.getCompanyName(),
            company.getCompanyLogo(),
            company.getTotalIncome(),
            company.getIndustry().toString(),
            company.getBusinessType().toString(),
            company.getCountry().toString(),
            company.getWebsite().toString(),
            company.getContacts().size(),
            company.getCreatedBy().getUsername(),
            company.getCreatedAt()
            );
    }
}
