package com.example.backend.Services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.backend.Entity.Contact;
import com.example.backend.Entity.Interaction;
import com.example.backend.EntityDto.InteractionDto;
import com.example.backend.EntityRepository.ContactRepository;
import com.example.backend.EntityRepository.InteractionRepository;
import com.example.backend.Enum.EInteraction;
import com.example.backend.Exception.ResourceNotFoundException;
import com.example.backend.IServices.IInteractionService;
import com.example.backend.Mapper.InteractionMapper;


@Service
public class InteractionService implements IInteractionService
{

    @Autowired
    private InteractionRepository interactionRepository;

    @Autowired
    private ContactRepository contactRepository;

    @Override
    public InteractionDto createInteraction(InteractionDto interactionDto) {
        Interaction interaction = InteractionMapper.mapToInteraction(interactionDto);
        Interaction savedInteraction = interactionRepository.save(interaction);
        return InteractionMapper.mapToInteractionDto(savedInteraction);
    }

    @Override
    public void deleteInteraction(Long interactionId) {
        Interaction interaction = interactionRepository.findById(interactionId)
                .orElseThrow(() -> new ResourceNotFoundException("Interaction Does not exist !"));
        interactionRepository.deleteById(interaction.getId());
        
    }

    @Override
    public List<InteractionDto> getAllInteractions() {
        List<Interaction> interactions = interactionRepository.findAll();
        return interactions.stream().map((interaction) -> InteractionMapper.mapToInteractionDto(interaction))
                .collect(Collectors.toList());
    }

    @Override
    public InteractionDto getInteractionById(Long interactionId) {
        Interaction interactionFinded = interactionRepository.findById(interactionId)
                .orElseThrow(() -> new ResourceNotFoundException("Interaction Does not exist !"));
        return InteractionMapper.mapToInteractionDto(interactionFinded);
    }

    @Override
    public List<InteractionDto> getInteractionsByContact(Long contactId) {
        Optional<Contact> contact = contactRepository.findById(contactId);
        if(contact.isPresent())
        {
            Contact contactFinded = contact.get();
            return contactFinded.getInteractions().stream().map(
                (interaction) -> InteractionMapper.mapToInteractionDto(interaction)
                ).collect(Collectors.toList());
        }
        else{
            throw new ResourceNotFoundException("contact seems to not exist");
        }
    }

    @Override
    public InteractionDto updateInteraction(Long interactionId, InteractionDto updatedInteractionDto) {
        Interaction interaction = interactionRepository.findById(interactionId)
                .orElseThrow(() -> new ResourceNotFoundException("Interaction Does not exist !"));
        interaction.setNotes(updatedInteractionDto.getNotes());
        interaction.setDate(updatedInteractionDto.getDate());
        interaction.setType(EInteraction.valueOf(updatedInteractionDto.getType()));

        Interaction updatedInteraction = interactionRepository.save(interaction);
        
        return InteractionMapper.mapToInteractionDto(updatedInteraction);
    }
    
}
