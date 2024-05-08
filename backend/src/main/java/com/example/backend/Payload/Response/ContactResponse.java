package com.example.backend.Payload.Response;

import java.util.Date;

public class ContactResponse {

    private Long id;

    private String fullName;
    
    private String email;

    private String phone;

    private byte[] image;

    private String timezone;

    private String company;

    private int CountInteractions;

    private String createdBy;

    private Date createdAt;

    public ContactResponse(Long id, String fullName, String email, String phone, byte[] image, String timezone,
            String company, int countInteractions, String createdBy, Date createdAt) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.phone = phone;
        this.image = image;
        this.timezone = timezone;
        this.company = company;
        CountInteractions = countInteractions;
        this.createdBy = createdBy;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public int getCountInteractions() {
        return CountInteractions;
    }

    public void setCountInteractions(int countInteractions) {
        CountInteractions = countInteractions;
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
