package com.example.backend.Mapper;

import java.util.stream.Collectors;

import com.example.backend.Entity.Contact;
import com.example.backend.EntityDto.ContactDto;

public class ContactMapper {
    public static ContactDto mapToContactDto(Contact contact)
    {
        return new ContactDto(
            contact.getId(),
            contact.getFirstName(),
            contact.getLastName(),
            contact.getEmail(),
            contact.getPhone(),
            contact.getImage(),
            contact.getTimeZone(),
            CompanyMapper.mapToCompanyDto(contact.getCompany()),
            contact.getCreatedAt(),
            contact.getCreatedBy(),
            contact.getInteractions().stream().map(
                InteractionMapper::mapToInteractionDto
            ).collect(Collectors.toList())
        );
    }
    public static Contact mapToContact(ContactDto contactDto)
    {
        return new Contact(
            contactDto.getFirstName(),
            contactDto.getLastName(),
            contactDto.getEmail(),
            contactDto.getPhone(),
            contactDto.getImage(),
            contactDto.getTimeZone(),
            CompanyMapper.mapToCompany(contactDto.getCompany()),
            contactDto.getInteractions().stream().map(
                InteractionMapper::mapToInteraction
            ).collect(Collectors.toList())
        );
    }
}
