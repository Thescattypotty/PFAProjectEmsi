package com.example.backend.Payload.Response;

import java.util.Date;

public class CompanyResponse {

    private Long id;

    private String companyName;

    private byte[] companyLogo;

    private double totalIncome;

    private String industry;

    private String businessType;

    private String country;

    private String website;

    private int contactsCount;

    private String createdBy;

    private Date createdAt;

    public CompanyResponse(Long id, String companyName, byte[] companyLogo, double totalIncome, String industry,
            String businessType, String country, String website, int contactsCount, String createdBy, Date createdAt) {
        this.id = id;
        this.companyName = companyName;
        this.companyLogo = companyLogo;
        this.totalIncome = totalIncome;
        this.industry = industry;
        this.businessType = businessType;
        this.country = country;
        this.website = website;
        this.contactsCount = contactsCount;
        this.createdBy = createdBy;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public byte[] getCompanyLogo() {
        return companyLogo;
    }

    public void setCompanyLogo(byte[] companyLogo) {
        this.companyLogo = companyLogo;
    }

    public double getTotalIncome() {
        return totalIncome;
    }

    public void setTotalIncome(double totalIncome) {
        this.totalIncome = totalIncome;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public String getBusinessType() {
        return businessType;
    }

    public void setBusinessType(String businessType) {
        this.businessType = businessType;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public int getContactsCount() {
        return contactsCount;
    }

    public void setContactsCount(int contactsCount) {
        this.contactsCount = contactsCount;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    
    
}
