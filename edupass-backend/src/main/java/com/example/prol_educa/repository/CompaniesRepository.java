package com.example.prol_educa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.prol_educa.entities.Companies;

@Repository
public interface CompaniesRepository extends JpaRepository<Companies, Integer> {
  Companies findByEmail(String email);
}