package com.example.backend.EntityRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.backend.Entity.Interaction;

@Repository
public interface InteractionRepository extends JpaRepository<Interaction, Long> 
{

}
