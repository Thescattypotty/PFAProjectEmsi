package com.example.backend.EntityDto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.example.backend.Entity.User;
import com.example.backend.Enum.EBusinessType;
import com.example.backend.Enum.ECompanySize;
import com.example.backend.Enum.ECountry;
import com.example.backend.Enum.EIndustry;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CompanyDto {
    private Long id;

    private String companyName;

    private byte[] companyLogo;

    private ECompanySize companySize;

    private double totalIncome;

    private EIndustry industry;

    private EBusinessType businessType;

    private ECountry country;

    private String website;

    private ContactDto salesOwner;

    private List<ContactDto> contacts = new ArrayList<>();

    private Date createdAt;

    private User createdBy;
}
