package com.example.prol_educa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.prol_educa.entities.Transactions;

@Repository
public interface TransactionsRepository extends JpaRepository<Transactions, Integer> {
  List<Transactions> findByEmpresaId_Id(Integer empresaId);
  Transactions findByCobrancaId(Number cobrancaId);
}