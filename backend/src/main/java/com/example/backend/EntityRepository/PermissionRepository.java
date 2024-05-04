package com.example.backend.EntityRepository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.backend.Entity.Permission;
import com.example.backend.Enum.EPermission;

@Repository
public interface PermissionRepository extends JpaRepository<Permission, Long> 
{
    Optional<Permission> findByName(EPermission name);
}
