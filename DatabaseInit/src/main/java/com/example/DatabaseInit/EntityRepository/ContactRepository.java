package com.example.DatabaseInit.EntityRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.DatabaseInit.Entity.Contact;


@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> 
{

}
