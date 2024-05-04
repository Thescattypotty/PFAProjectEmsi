package com.example.backend.IServices;

import java.util.List;
import com.example.backend.EntityDto.InteractionDto;

public interface IInteractionService {
    
    InteractionDto createInteraction(InteractionDto interactionDto);

    InteractionDto getInteractionById(Long interactionId);

    List<InteractionDto> getInteractionsByContact(Long contactId);

    List<InteractionDto> getAllInteractions();

    InteractionDto updateInteraction(Long interactionId, InteractionDto updatedInteractionDto);

    void deleteInteraction(Long interactionId);
    
}
