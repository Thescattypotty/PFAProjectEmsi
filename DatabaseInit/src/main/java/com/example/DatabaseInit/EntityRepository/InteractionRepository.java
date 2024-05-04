package com.example.DatabaseInit.EntityRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.DatabaseInit.Entity.Interaction;

@Repository
public interface InteractionRepository extends JpaRepository<Interaction, Long> 
{

}
