package com.example.backend.MapToResponse;

import com.example.backend.Entity.Interaction;
import com.example.backend.Payload.Response.InteractionResponse;

public class InteractionMapperResponse {
    
    public static InteractionResponse mapToInteractionResponse(Interaction interaction)
    {
        return new InteractionResponse(
            interaction.getId(),
            interaction.getType().toString(),
            interaction.getNotes(),
            interaction.getDate(),
            interaction.getContact().getFirstName() + " " + interaction.getContact().getLastName(),
            interaction.getCreatedBy().getUsername(),
            interaction.getCreatedAt()
        );
    }
}
