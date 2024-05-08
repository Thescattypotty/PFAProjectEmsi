package com.example.backend.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.backend.Entity.Contact;
import com.example.backend.Entity.Interaction;
import com.example.backend.EntityRepository.ContactRepository;
import com.example.backend.EntityRepository.InteractionRepository;
import com.example.backend.Exception.ResourceNotFoundException;
import com.example.backend.IServices.IInteractionService;


@Service
public class InteractionService implements IInteractionService
{

    @Autowired
    private InteractionRepository interactionRepository;

    @Autowired
    private ContactRepository contactRepository;

    @Override
    public Interaction createInteraction(Interaction interactionDto) {
        return interactionRepository.save(interactionDto);
    }

    @Override
    public void deleteInteraction(Long interactionId) {
        Interaction interaction = interactionRepository.findById(interactionId)
                .orElseThrow(() -> new ResourceNotFoundException("Interaction Does not exist !"));
        interactionRepository.deleteById(interaction.getId());
        
    }

    @Override
    public List<Interaction> getAllInteractions() {
        return interactionRepository.findAll();
    }

    @Override
    public Interaction getInteractionById(Long interactionId) {
        Interaction interactionFinded = interactionRepository.findById(interactionId)
                .orElseThrow(() -> new ResourceNotFoundException("Interaction Does not exist !"));
        return interactionFinded;
    }

    @Override
    public List<Interaction> getInteractionsByContact(Long contactId) {
        Optional<Contact> contact = contactRepository.findById(contactId);
        if(contact.isPresent())
        {
            Contact contactFinded = contact.get();
            return contactFinded.getInteractions();
        }
        else{
            throw new ResourceNotFoundException("contact seems to not exist");
        }
    }

    @Override
    public Interaction updateInteraction(Long interactionId, Interaction updatedInteractionDto) {
        Interaction interaction = interactionRepository.findById(interactionId)
                .orElseThrow(() -> new ResourceNotFoundException("Interaction Does not exist !"));

        interaction.setNotes(updatedInteractionDto.getNotes());
        interaction.setDate(updatedInteractionDto.getDate());
        interaction.setType(updatedInteractionDto.getType());
        
        return interactionRepository.save(interaction);
    }
    
}
