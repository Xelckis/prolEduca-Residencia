package com.example.prol_educa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.prol_educa.entities.Customers;

@Repository
public interface CustomersRepository extends JpaRepository<Customers, Integer> {
  Customers findByEmail(String email);

  Customers findByFullName(String fullName);

  Customers findByCpf(String cpf);

  List<Customers> findByEmpresaId(Integer empresaId);
}
