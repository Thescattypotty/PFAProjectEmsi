package com.example.backend.IServices;

import java.util.List;

import com.example.backend.Entity.Company;
import com.example.backend.Entity.Contact;
import com.example.backend.Entity.Interaction;

public interface IContactService {
    
    Contact createContact(Contact contactDto);

    Contact getContactById(Long contactId);

    List<Contact> getAllContacts();

    Contact updateContact(Long contactId , Contact updateContactDto);

    void deleteContact(Long contactId);
    
    Contact AddInteractionToContact(Long contactId, Interaction updateContactDto);

    Contact updateContactCompany(Long contactId , Company company);
}
