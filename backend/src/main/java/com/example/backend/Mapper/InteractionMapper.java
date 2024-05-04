package com.example.backend.Mapper;

import com.example.backend.Entity.Interaction;
import com.example.backend.EntityDto.InteractionDto;
import com.example.backend.Enum.EInteraction;

public class InteractionMapper {
    public static InteractionDto mapToInteractionDto(Interaction interaction) {
        if(interaction == null)
        {
            return null;
        }
        return new InteractionDto(
            interaction.getId(),
            interaction.getType().name(),
            interaction.getNotes(),
            interaction.getDate(),
            interaction.getContact(),
            interaction.getCreatedAt()
        );
    }

    public static Interaction mapToInteraction(InteractionDto interactionDto) {
        if (interactionDto == null) {
            return null;
        }
        return new Interaction(
            EInteraction.valueOf(interactionDto.getType()),
            interactionDto.getNotes(),
            interactionDto.getDate(),
            interactionDto.getContact()
        );
    }
}
