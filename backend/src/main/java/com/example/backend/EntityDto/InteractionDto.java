package com.example.backend.EntityDto;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.util.Date;

import com.example.backend.Entity.Contact;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InteractionDto {
    private Long id;
    private String type;
    private String notes;
    private Date date;
    private Contact contact;
    private Date CreatedAt;
}
