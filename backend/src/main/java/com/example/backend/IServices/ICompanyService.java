package com.example.backend.IServices;

import java.util.List;

import com.example.backend.Entity.Company;
import com.example.backend.Entity.Contact;

public interface ICompanyService {

    Company createCompany(Company companyDto);

    Company getCompanyById(Long companyId);

    List<Company> getAllCompanys();

    void deleteCompany(Long companyId);

    Company addContactToCompany(Long companyId , Contact contactDto);

    Company updateCompany(Long companyId , Company updatedCmCompanyDto);
}

