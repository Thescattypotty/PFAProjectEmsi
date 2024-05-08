package com.example.backend.Payload.Response;

import java.util.Date;

public class InteractionResponse {


    private Long id;

    private String typeOfInteraction;

    private String notes ;

    private Date date;

    private String contact;

    private String createdBy;

    private Date createdAt;

    public InteractionResponse(Long id, String typeOfInteraction, String notes, Date date, String contact,
            String createdBy, Date createdAt) {
        this.id = id;
        this.typeOfInteraction = typeOfInteraction;
        this.notes = notes;
        this.date = date;
        this.contact = contact;
        this.createdBy = createdBy;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTypeOfInteraction() {
        return typeOfInteraction;
    }

    public void setTypeOfInteraction(String typeOfInteraction) {
        this.typeOfInteraction = typeOfInteraction;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
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
