package com.example.backend.MapToResponse;

import com.example.backend.Entity.Contact;
import com.example.backend.Payload.Response.ContactResponse;

public class ContactMapperResponse {
    

    public static ContactResponse MapToContactResponse(Contact contact)
    {
        return new ContactResponse(
            contact.getId(),
            contact.getFirstName()+" " +contact.getLastName(),
            contact.getEmail(),
            contact.getPhone(),
            contact.getImage(),
            contact.getTimeZone().toString(),
            contact.getCompany().getCompanyName(),
            contact.getInteractions().size(),
            contact.getCreatedBy().getUsername(),
            contact.getCreatedAt()
        );
    }
}
