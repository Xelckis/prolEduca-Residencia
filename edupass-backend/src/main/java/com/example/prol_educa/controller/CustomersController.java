package com.example.prol_educa.controller;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.prol_educa.entities.Customers;
import com.example.prol_educa.models.CustomersDto;
import com.example.prol_educa.service.CustomersService;
import com.example.prol_educa.service.AuthorizedUsersService;

@RestController
@RequestMapping("/customers")
public class CustomersController {

  @Autowired
  public CustomersService service;

    @Autowired
  public AuthorizedUsersService AuthorizedUsersService;

  @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody CustomersDto dto) {
      try {
          service.create(dto);
          return ResponseEntity.ok(Collections.singletonMap("message", "Cliente cadastrado com sucesso"));
      } catch (RuntimeException e) {
          return ResponseEntity.badRequest().body(Collections.singletonMap("message", e.getMessage()));
      } catch (Exception e) {
          e.printStackTrace();
          return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
              .body(Collections.singletonMap("message", "Erro ao cadastrar cliente. Tente novamente."));
      }
    }

  @GetMapping
  @PreAuthorize("hasRole('ADMIN') or hasRole('COMPANY')")
  public ResponseEntity<?> findAll() {
    try {
      List<Customers> customers = service.findAll();
      return ResponseEntity.ok(customers);
    } catch (Exception e) {
      e.printStackTrace();
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
          .body(Collections.singletonMap("message", "Erro ao buscar clientes."));
    }
  }

  @GetMapping("/{id}")
  public ResponseEntity<?> findById(@PathVariable("id") Integer id) {
    if (id == null || id <= 0) {
      return ResponseEntity.badRequest()
          .body(Collections.singletonMap("message", "ID inválido"));
    }

    try {
      Customers customer = service.findById(id);
      return ResponseEntity.ok(customer);
    } catch (Exception e) {
      e.printStackTrace();
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
          .body(Collections.singletonMap("message", "Erro ao buscar cliente."));
    }
  }

  @PutMapping("/{id}")
  // @PreAuthorize("hasRole('ADMIN')")
  public ResponseEntity<?> update(@PathVariable("id") Integer id, @RequestBody CustomersDto dto) {
    if (id == null || id <= 0) {
      return ResponseEntity.badRequest()
          .body(Collections.singletonMap("message", "ID inválido"));
    }
    
    if (dto == null /* || outras validações aqui, se desejar */) {
      return ResponseEntity.badRequest()
          .body(Collections.singletonMap("message", "Dados do cliente inválidos ou incompletos"));
    }

    try {
      service.update(id, dto);
      return ResponseEntity.ok(Collections.singletonMap("message", "Cliente atualizado com sucesso"));
    } catch (Exception e) {
      e.printStackTrace();
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
          .body(Collections.singletonMap("message", "Erro ao atualizar cliente."));
    }
  }

  @DeleteMapping("/{id}")
  @PreAuthorize("hasRole('ADMIN') or hasRole('COMPANY')")
  public ResponseEntity<?> delete(@PathVariable("id") Integer id) {
    if (id == null || id <= 0) {
      return ResponseEntity.badRequest()
          .body(Collections.singletonMap("message", "ID inválido"));
    }

    try {
      service.delete(id);
      return ResponseEntity.ok(Collections.singletonMap("message", "Cliente deletado com sucesso"));
    } catch (DataIntegrityViolationException ex) {
      return ResponseEntity.status(HttpStatus.CONFLICT)
          .body(Collections.singletonMap("message", "Não é possível excluir o cliente, pois ele está vinculado a um bolsista."));
    } catch (Exception ex) {
      ex.printStackTrace();
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
          .body(Collections.singletonMap("message", "Erro inesperado ao tentar excluir o cliente."));
    }
  }

  @GetMapping("/company/{id}")
  @PreAuthorize("hasRole('ADMIN') or hasRole('COMPANY')")
  public ResponseEntity<?> getCustomersByCompany(@PathVariable Integer id) {
    if (id == null || id <= 0) {
      return ResponseEntity.badRequest()
          .body(Collections.singletonMap("message", "ID inválido"));
    }

    try {
      List<Customers> customers = service.findByCompanyId(id);
      return ResponseEntity.ok(customers);
    } catch (Exception e) {
      e.printStackTrace();
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
          .body(Collections.singletonMap("message", "Erro ao buscar clientes da empresa."));
    }
  }

}
