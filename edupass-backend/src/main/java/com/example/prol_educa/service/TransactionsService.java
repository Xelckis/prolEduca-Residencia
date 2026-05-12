package com.example.prol_educa.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.prol_educa.entities.Transactions;
import com.example.prol_educa.repository.TransactionsRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class TransactionsService {

  @Autowired
  private TransactionsRepository transactionsRepository;

  public Transactions create(Transactions transaction) {
    return transactionsRepository.save(transaction);
  }

  public List<Transactions> findAll() {
    return transactionsRepository.findAll();
  }

  public Transactions findById(Integer id) {
    Optional<Transactions> transaction = transactionsRepository.findById(id);
    return transaction.orElseThrow(() -> new EntityNotFoundException("Transação com ID " + id + " não encontrada."));
  }

  public Transactions findByChargeId(Number id) {
    Transactions transaction = transactionsRepository.findByCobrancaId(id);
    if (transaction == null) {
      throw new EntityNotFoundException("Transação com ID " + id + " não encontrada.");
    }
    return transaction;
  }

  public Transactions update(Integer id, Transactions updatedTransaction) {
    Transactions existing = findById(id);
    existing.setEmpresaId(updatedTransaction.getEmpresaId());
    existing.setMetodoPagamento(updatedTransaction.getMetodoPagamento());
    existing.setValor(updatedTransaction.getValor());
    existing.setStatus(updatedTransaction.getStatus());

    return transactionsRepository.save(existing);
  }

  public void delete(Integer id) {
    Transactions existing = findById(id);
    transactionsRepository.delete(existing);
  }
}
