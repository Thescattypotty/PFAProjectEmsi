package com.example.backend.EntityRepository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.backend.Entity.Role;
import com.example.backend.Enum.ERole;

@Repository
public interface RoleRepository extends JpaRepository<Role , Long>
{
    Optional<Role> findByName(ERole name);
}
