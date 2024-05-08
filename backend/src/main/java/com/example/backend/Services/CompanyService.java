package com.example.backend.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.backend.Entity.Company;
import com.example.backend.Entity.Contact;
import com.example.backend.EntityRepository.CompanyRepository;
import com.example.backend.EntityRepository.ContactRepository;
import com.example.backend.Exception.ResourceNotFoundException;
import com.example.backend.IServices.ICompanyService;



@Service
public class CompanyService implements ICompanyService
{
    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private ContactService contactService;
    @Autowired
    private ContactRepository contactRepository;

    @Override
    public Company addContactToCompany(Long companyId, Contact contactDto) {
        Company company = companyRepository.findById(companyId)
                .orElseThrow(() -> new ResourceNotFoundException("Company Does Not exist !"));
        Contact newContact = contactService.createContact(contactDto);

        Contact contact = contactRepository.findById(newContact.getId())
            .orElseThrow(() -> new ResourceNotFoundException("contact Could not be found"));

        company.addContact(contact);

        return companyRepository.save(company);
    }

    @Override
    public Company createCompany(Company companyDto) {
        return companyRepository.save(companyDto);
    }

    @Override
    public void deleteCompany(Long companyId) {
        Company company = companyRepository.findById(companyId)
            .orElseThrow(() -> new ResourceNotFoundException("Company Does Not exist !"));
        companyRepository.deleteById(company.getId());
    }

    @Override
    public List<Company> getAllCompanys() {
        return companyRepository.findAll();
    }

    @Override
    public Company getCompanyById(Long companyId) {
        Company company = companyRepository.findById(companyId)
                .orElseThrow(() -> new ResourceNotFoundException("Company Does Not exist !"));
        return company;
    }

    @Override
    public Company updateCompany(Long companyId, Company updatedCmCompanyDto) {
        // TODO Auto-generated method stub
        return null;
    }
    
    
}
