package com.example.DatabaseInit.EntityRepository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.DatabaseInit.Entity.Permission;
import com.example.DatabaseInit.Enum.EPermission;

@Repository
public interface PermissionRepository extends JpaRepository<Permission, Long> 
{
    Optional<Permission> findByName(EPermission name);
}
