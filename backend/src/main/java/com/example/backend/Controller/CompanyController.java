package com.example.backend.Controller;

import java.util.List;

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
import com.example.backend.EntityDto.CompanyDto;
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
    public ResponseEntity<CompanyDto> createCompany(@RequestBody CompanyDto companyDto)
    {
        CompanyDto SavedCOmpanyDto = companyService.createCompany(companyDto);
        return new ResponseEntity<>(SavedCOmpanyDto, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    @RequiresPermission({"ACCESS_COMPANY"})
    public ResponseEntity<CompanyDto> getCompanyById(@PathVariable("id") Long companyId)
    {
        CompanyDto companyDto = companyService.getCompanyById(companyId);
        return ResponseEntity.ok(companyDto);
    }

    @GetMapping
    @RequiresPermission({ "ACCESS_COMPANY" })
    public ResponseEntity<List<CompanyDto>> getAllCompanys() {
        List<CompanyDto> companys = companyService.getAllCompanys();
        return ResponseEntity.ok(companys);
    }

    @PutMapping("{id}")
    @RequiresPermission({"MODIFY_COMPANY"})
    public ResponseEntity<CompanyDto> updateCompany(@PathVariable("id")Long companyId, CompanyDto UpdatedCompanyDto)
    {
        CompanyDto company = companyService.updateCompany(companyId, UpdatedCompanyDto);
        return ResponseEntity.ok(company);
    }

    @DeleteMapping("{id}")
    @RequiresPermission({"DELETE_COMPANY"})
    public ResponseEntity<String> deleteCompany(@PathVariable("id") Long companyId)
    {
        companyService.deleteCompany(companyId);
        return ResponseEntity.ok("company Deleted Successfully !");
    }
}
