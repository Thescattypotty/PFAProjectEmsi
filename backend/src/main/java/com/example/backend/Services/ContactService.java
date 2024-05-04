package com.example.backend.Services;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.backend.Entity.Company;
import com.example.backend.Entity.Contact;
import com.example.backend.Entity.Interaction;
import com.example.backend.EntityDto.ContactDto;
import com.example.backend.EntityDto.InteractionDto;
import com.example.backend.EntityRepository.ContactRepository;
import com.example.backend.EntityRepository.InteractionRepository;
import com.example.backend.Exception.ResourceNotFoundException;
import com.example.backend.IServices.IContactService;
import com.example.backend.Mapper.ContactMapper;


@Service
public class ContactService implements IContactService
{
    @Autowired
    private ContactRepository contactRepository;

    @Autowired
    private InteractionService interactionService;

    @Autowired
    private InteractionRepository interactionRepository;

    @Override
    public ContactDto createContact(ContactDto contactDto) {
        Contact contact = ContactMapper.mapToContact(contactDto);
        Contact savedContact = contactRepository.save(contact);
        return ContactMapper.mapToContactDto(savedContact);
    }

    @Override
    public void deleteContact(Long contactId) {
        Contact contact = contactRepository.findById(contactId)
                .orElseThrow(() -> new ResourceNotFoundException("contact Does not exist !"));
        contactRepository.deleteById(contact.getId());
    }

    @Override
    public List<ContactDto> getAllContacts() {
        List<Contact> contacts = contactRepository.findAll();
        return contacts.stream().map((contact) -> ContactMapper.mapToContactDto(contact)).collect(Collectors.toList());
    }

    @Override
    public ContactDto getContactById(Long contactId) {
        Contact contactFinded = contactRepository.findById(contactId)
                .orElseThrow(() -> new ResourceNotFoundException("contact Does not exist !"));
        return ContactMapper.mapToContactDto(contactFinded);
    }

    @Override
    public ContactDto updateContact(Long contactId, ContactDto updateContactDto) {
        Contact contact = contactRepository.findById(contactId)
                .orElseThrow(() -> new ResourceNotFoundException("contact Does not exist !"));
        contact.setFirstName(updateContactDto.getFirstName());
        contact.setLastName(updateContactDto.getLastName());
        contact.setEmail(updateContactDto.getEmail());
        contact.setPhone(updateContactDto.getPhone());
        //get the company from the db
        //contact.setCompany(updateContactDto.getCompany());
        //get the interaction from the db
        //contact.setInteractions(updateContactDto.getInteractions());
        Contact Updatedcontact = contactRepository.save(contact);
        return ContactMapper.mapToContactDto(Updatedcontact);
    }
    @Override
    public ContactDto updateContactCompany(Long contactId , Company company)
    {
        Contact contact = contactRepository.findById(contactId)
                .orElseThrow(() -> new ResourceNotFoundException("contact Does not exist !"));
        
        contact.setCompany(company);
        
        Contact UpdatedContact = contactRepository.save(contact);
        return ContactMapper.mapToContactDto(UpdatedContact);
    }


    @Override 
    public ContactDto AddInteractionToContact(Long contactId, InteractionDto updateContactDto)
    {
        Contact contact = contactRepository.findById(contactId)
                .orElseThrow(() -> new ResourceNotFoundException("contact Does not exist !"));
        
        InteractionDto newInteraction = interactionService.createInteraction(updateContactDto);

        Interaction interaction = interactionRepository.findById(newInteraction.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Interaction Could not be found"))
            ;
        contact.addInteraction(interaction);

        Contact UpdatedContact = contactRepository.save(contact);
        return ContactMapper.mapToContactDto(UpdatedContact);
    }
    
}
