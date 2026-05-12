package com.example.prol_educa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.prol_educa.entities.Registrations;

@Repository
public interface RegistrationsRepository extends JpaRepository<Registrations, Integer> {

  @Query("""
          SELECT r
          FROM Registrations r
          WHERE r.scholarshipHolders.customers.empresa.id = :companyId
      """)
  List<Registrations> findAllByCompanyId(@Param("companyId") Integer companyId);

}
