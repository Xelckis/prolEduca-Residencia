package com.example.prol_educa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.prol_educa.entities.StatusRegistrations;

@Repository
public interface StatusRegistrationsRepository extends JpaRepository<StatusRegistrations, Integer> {
  StatusRegistrations findById(int id);
}
