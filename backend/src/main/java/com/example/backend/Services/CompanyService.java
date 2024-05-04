package com.example.backend.Services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.backend.Entity.Company;
import com.example.backend.Entity.Contact;
import com.example.backend.EntityDto.CompanyDto;
import com.example.backend.EntityDto.ContactDto;
import com.example.backend.EntityRepository.CompanyRepository;
import com.example.backend.EntityRepository.ContactRepository;
import com.example.backend.Exception.ResourceNotFoundException;
import com.example.backend.IServices.ICompanyService;
import com.example.backend.Mapper.CompanyMapper;



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
    public CompanyDto addContactToCompany(Long companyId, ContactDto contactDto) {
        Company company = companyRepository.findById(companyId)
                .orElseThrow(() -> new ResourceNotFoundException("Company Does Not exist !"));
        ContactDto newContact = contactService.createContact(contactDto);

        Contact contact = contactRepository.findById(newContact.getId())
            .orElseThrow(() -> new ResourceNotFoundException("contact Could not be found"));

        company.addContact(contact);
        Company updatedCompany = companyRepository.save(company);

        return CompanyMapper.mapToCompanyDto(updatedCompany);
    }

    @Override
    public CompanyDto createCompany(CompanyDto companyDto) {
        Company company = CompanyMapper.mapToCompany(companyDto);
        Company SavedCompany = companyRepository.save(company);
        return CompanyMapper.mapToCompanyDto(SavedCompany);
    }

    @Override
    public void deleteCompany(Long companyId) {
        Company company = companyRepository.findById(companyId)
            .orElseThrow(() -> new ResourceNotFoundException("Company Does Not exist !"));
        companyRepository.deleteById(company.getId());
    }

    @Override
    public List<CompanyDto> getAllCompanys() {
        List<Company> companys = companyRepository.findAll();
        return companys.stream().map((company) -> CompanyMapper.mapToCompanyDto(company)).collect(Collectors.toList());
    }

    @Override
    public CompanyDto getCompanyById(Long companyId) {
        Company company = companyRepository.findById(companyId)
                .orElseThrow(() -> new ResourceNotFoundException("Company Does Not exist !"));
        return CompanyMapper.mapToCompanyDto(company);
    }

    @Override
    public CompanyDto updateCompany(Long companyId, CompanyDto updatedCmCompanyDto) {
        // TODO Auto-generated method stub
        return null;
    }
    
    
}
