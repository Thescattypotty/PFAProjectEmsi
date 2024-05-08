package com.example.backend.Entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import java.util.List;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.Date;

import com.example.backend.EntityListener.CreatedByListener;
import com.example.backend.Enum.ETimeZone;

import java.util.ArrayList;


@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "contacts")
@EntityListeners(CreatedByListener.class)
public class Contact extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "firstName", nullable = false)
    private String firstName;

    @Column(name = "lastName", nullable = false)
    private String lastName;

    @Email
    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "phone", nullable = true)
    private String phone;

    @Column(name = "image", nullable = true)
    @Lob
    private byte[] image;

    @Enumerated(EnumType.STRING)
    @Column(name = "timezone", nullable = true)
    private ETimeZone timeZone;

    @ManyToOne
    private Company company;

    @OneToMany(mappedBy = "contact", cascade = CascadeType.REMOVE, orphanRemoval = true)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<Interaction> interactions = new ArrayList<>();

    public Contact(String firstName, String lastName, @Email String email, String phone, byte[] image,
            ETimeZone timeZone,
            Company company, List<Interaction> interactions) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.image = image;
        this.timeZone = timeZone;
        this.company = company;
        this.interactions = interactions;
    }

    public Contact(String firstName, String lastName, @Email String email, String phone, byte[] image,
            ETimeZone timeZone, User createdBy, Date createdAt) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.image = image;
        this.timeZone = timeZone;
        this.setCreatedBy(createdBy);
        this.setCreatedAt(createdAt);
    }

    public Contact(String firstName, String lastName, @Email String email, String phone, byte[] image,
            ETimeZone timeZone, User createdBy, Date createdAt, Company company) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.image = image;
        this.timeZone = timeZone;
        this.company = company;
    }

    public void addInteraction(Interaction interaction) {
        this.interactions.add(interaction);
    }

    public void addInteractions(List<Interaction> interactions) {
        this.interactions.addAll(interactions);
    }

}