package com.example.backend.EntityDto;

import java.util.Date;
import java.util.List;

import com.example.backend.Entity.User;
import com.example.backend.Enum.ETimeZone;

import java.util.ArrayList;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ContactDto {
    private Long id;

    private String firstName;

    private String lastName;

    private String email;

    private String phone;

    private byte[] image;

    private ETimeZone timeZone;
    
    private CompanyDto company;

    private Date CreatedAt;

    private User CreatedBy;

    private List<InteractionDto> interactions = new ArrayList<>();
    
}
