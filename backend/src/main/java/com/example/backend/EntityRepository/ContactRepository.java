package com.example.backend.EntityRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.backend.Entity.Contact;


@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> 
{

}
