package com.example.backend.Services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.backend.Entity.Company;
import com.example.backend.Entity.Contact;
import com.example.backend.Entity.Interaction;

import com.example.backend.EntityRepository.ContactRepository;
import com.example.backend.EntityRepository.InteractionRepository;
import com.example.backend.Exception.ResourceNotFoundException;
import com.example.backend.IServices.IContactService;


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
    public Contact createContact(Contact contactDto) {
        return contactRepository.save(contactDto);
    }

    @Override
    public void deleteContact(Long contactId) {
        Contact contact = contactRepository.findById(contactId)
                .orElseThrow(() -> new ResourceNotFoundException("contact Does not exist !"));
        contactRepository.deleteById(contact.getId());
    }

    @Override
    public List<Contact> getAllContacts() {
        return contactRepository.findAll();
    }

    @Override
    public Contact getContactById(Long contactId) {
        Contact contactFinded = contactRepository.findById(contactId)
                .orElseThrow(() -> new ResourceNotFoundException("contact Does not exist !"));
        return contactFinded;
    }

    @Override
    public Contact updateContact(Long contactId, Contact updateContactDto) {
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
        return contactRepository.save(contact);
    }
    @Override
    public Contact updateContactCompany(Long contactId , Company company)
    {
        Contact contact = contactRepository.findById(contactId)
                .orElseThrow(() -> new ResourceNotFoundException("contact Does not exist !"));
        
        contact.setCompany(company);
        
        return contactRepository.save(contact);
    }


    @Override 
    public Contact AddInteractionToContact(Long contactId, Interaction updateContactDto)
    {
        Contact contact = contactRepository.findById(contactId)
                .orElseThrow(() -> new ResourceNotFoundException("contact Does not exist !"));
        
        Interaction newInteraction = interactionService.createInteraction(updateContactDto);

        Interaction interaction = interactionRepository.findById(newInteraction.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Interaction Could not be found"))
            ;
        contact.addInteraction(interaction);

        return contactRepository.save(contact);
    }
    
}
