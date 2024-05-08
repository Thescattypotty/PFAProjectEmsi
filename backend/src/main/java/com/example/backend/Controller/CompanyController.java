package com.example.backend.Controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.backend.Annotation.RequiresPermission;
import com.example.backend.Annotation.RequiresRole;
import com.example.backend.Entity.Company;
import com.example.backend.MapToResponse.CompanyMapperResponse;
import com.example.backend.Payload.Response.CompanyResponse;
import com.example.backend.Services.CompanyService;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/company")
@RequiresRole({ "ROLE_USER" })
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    @PostMapping
    @RequiresPermission({"CREATE_COMPANY"})
    public ResponseEntity<CompanyResponse> createCompany(@RequestBody Company companyDto)
    {
        Company SavedCompanyDto = companyService.createCompany(companyDto);
        return new ResponseEntity<>(CompanyMapperResponse.MapToCompanyResponse(SavedCompanyDto), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    @RequiresPermission({"ACCESS_COMPANY"})
    public ResponseEntity<CompanyResponse> getCompanyById(@PathVariable("id") Long companyId)
    {
        Company companyDto = companyService.getCompanyById(companyId);
        return ResponseEntity.ok(CompanyMapperResponse.MapToCompanyResponse(companyDto));
    }

    @GetMapping
    @RequiresPermission({ "ACCESS_COMPANY" })
    public ResponseEntity<List<CompanyResponse>> getAllCompanys() {
        List<Company> companys = companyService.getAllCompanys();
        return ResponseEntity.ok(
            companys.stream().map(CompanyMapperResponse::MapToCompanyResponse)
            .collect(Collectors.toList())
        );
    }

    @PutMapping("/{id}")
    @RequiresPermission({"MODIFY_COMPANY"})
    public ResponseEntity<CompanyResponse> updateCompany(@PathVariable("id")Long companyId, Company UpdatedCompanyDto)
    {
        Company company = companyService.updateCompany(companyId, UpdatedCompanyDto);
        return ResponseEntity.ok(CompanyMapperResponse.MapToCompanyResponse(company));
    }

    @DeleteMapping("/{id}")
    @RequiresPermission({"DELETE_COMPANY"})
    public ResponseEntity<String> deleteCompany(@PathVariable("id") Long companyId)
    {
        companyService.deleteCompany(companyId);
        return ResponseEntity.ok("company Deleted Successfully !");
    }
}
