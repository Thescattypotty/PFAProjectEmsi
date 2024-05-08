package com.example.backend.IServices;

import java.util.List;
import com.example.backend.Entity.Interaction;

public interface IInteractionService {
    
    Interaction createInteraction(Interaction interactionDto);

    Interaction getInteractionById(Long interactionId);

    List<Interaction> getInteractionsByContact(Long contactId);

    List<Interaction> getAllInteractions();

    Interaction updateInteraction(Long interactionId, Interaction updatedInteractionDto);

    void deleteInteraction(Long interactionId);
    
}
