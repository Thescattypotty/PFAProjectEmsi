package com.example.backend.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.util.Date;

import com.example.backend.EntityListener.CreatedByListener;
import com.example.backend.Enum.EInteraction;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "interactions")
@EntityListeners(CreatedByListener.class)
public class Interaction extends BaseEntity
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="type", nullable = false)
    private EInteraction type;

    @Column(name = "notes", nullable = true)
    private String notes;

    @Column(name = "date", nullable = true)
    private Date date;

    @ManyToOne
    private Contact contact;


    public Interaction(EInteraction type, String notes, Date date) {
        this.type = type;
        this.notes = notes;
        this.date = date;
    }

    public Interaction(EInteraction type, String notes, Date date, Contact contact) {
        this.type = type;
        this.notes = notes;
        this.date = date;
        this.contact = contact;
    }
    
    
}