package com.example.backend.IServices;

import java.util.List;

import com.example.backend.Entity.Company;
import com.example.backend.EntityDto.ContactDto;
import com.example.backend.EntityDto.InteractionDto;

public interface IContactService {
    
    ContactDto createContact(ContactDto contactDto);

    ContactDto getContactById(Long contactId);

    List<ContactDto> getAllContacts();

    ContactDto updateContact(Long contactId , ContactDto updateContactDto);

    void deleteContact(Long contactId);
    
    ContactDto AddInteractionToContact(Long contactId, InteractionDto updateContactDto);

    ContactDto updateContactCompany(Long contactId , Company company);
}
