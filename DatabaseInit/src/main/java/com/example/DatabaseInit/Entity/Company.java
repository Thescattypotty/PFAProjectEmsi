package com.example.DatabaseInit.Entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import java.util.List;

import com.example.DatabaseInit.Enum.EBusinessType;
import com.example.DatabaseInit.Enum.ECompanySize;
import com.example.DatabaseInit.Enum.ECountry;
import com.example.DatabaseInit.Enum.EIndustry;

import java.util.ArrayList;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "company")
public class Company extends BaseEntity
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="company_name", nullable = false)
    private String companyName;

    @Column(name = "company_logo", nullable = true)
    @Lob
    private byte[] companyLogo;

    @Enumerated(EnumType.STRING)
    @Column(name = "company_size", nullable = true)
    private ECompanySize companySize;

    @Column(name = "total_income", nullable = true)
    private double totalIncome;

    @Enumerated(EnumType.STRING)
    @Column(name = "industry", nullable = true)
    private EIndustry industry;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "business_type", nullable = true)
    private EBusinessType businessType;

    @Enumerated(EnumType.STRING)
    @Column(name = "country", nullable = true)
    private ECountry country;

    @Column(name = "website", nullable = true)
    private String website;

    @ManyToOne(optional = true)
    private Contact salesOwner;

    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL , orphanRemoval = true)
    private List<Contact> contacts = new ArrayList<>();

    public Company(String companyName, byte[] companyLogo, ECompanySize companySize, double totalIncome,
            EIndustry industry, EBusinessType businessType, ECountry country, String website, Contact salesOwner,
            List<Contact> contacts) {
        this.companyName = companyName;
        this.companyLogo = companyLogo;
        this.companySize = companySize;
        this.totalIncome = totalIncome;
        this.industry = industry;
        this.businessType = businessType;
        this.country = country;
        this.website = website;
        this.salesOwner = salesOwner;
        this.contacts = contacts;
    }
    
    public Company(String companyName, byte[] companyLogo, ECompanySize companySize, double totalIncome,
            EIndustry industry, EBusinessType businessType, ECountry country, String website, Contact salesOwner) {
        this.companyName = companyName;
        this.companyLogo = companyLogo;
        this.companySize = companySize;
        this.totalIncome = totalIncome;
        this.industry = industry;
        this.businessType = businessType;
        this.country = country;
        this.website = website;
        this.salesOwner = salesOwner;
    }

    public void addContact(Contact contact)
    {
        this.contacts.add(contact);
    }

    public void addContacts(List<Contact> contacts) {
        this.contacts.addAll(contacts);
    }
}
