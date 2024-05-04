package com.example.backend.EntityRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.backend.Entity.Company;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {

}
