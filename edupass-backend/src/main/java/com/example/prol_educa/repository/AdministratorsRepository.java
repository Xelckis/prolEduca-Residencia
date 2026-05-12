package com.example.prol_educa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.prol_educa.entities.Administrators;

@Repository
public interface AdministratorsRepository extends JpaRepository<Administrators, Integer> {
  Administrators findByEmail(String email);
}
