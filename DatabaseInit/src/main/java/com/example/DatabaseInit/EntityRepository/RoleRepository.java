package com.example.DatabaseInit.EntityRepository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.DatabaseInit.Entity.Role;
import com.example.DatabaseInit.Enum.ERole;

@Repository
public interface RoleRepository extends JpaRepository<Role , Long>
{
    Optional<Role> findByName(ERole name);
}
