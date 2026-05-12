package com.example.prol_educa.controller;

import java.util.List;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.example.prol_educa.entities.Institutions;
import com.example.prol_educa.models.InstitutionsDto;
import com.example.prol_educa.service.InstitutionsService;

@RestController
@RequestMapping("/institutions")
public class InstitutionsController {

  @Autowired
  public InstitutionsService service;

  @PostMapping("/create")
  @PreAuthorize("hasRole('ADMIN')")
  public ResponseEntity<?> create(@RequestBody InstitutionsDto dto) {
    if (dto == null || dto.getName() == null || dto.getName().isBlank() /* ajuste validação */) {
      return ResponseEntity.badRequest()
          .body(Collections.singletonMap("message", "Dados da instituição inválidos ou incompletos"));
    }
    try {
      service.create(dto);
      return ResponseEntity.ok(Collections.singletonMap("message", "Cadastro da instituição realizado com sucesso!"));
    } catch (Exception e) {
      e.printStackTrace();
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
          .body(Collections.singletonMap("message", "Erro ao cadastrar instituição. Tente novamente."));
    }
  }

  @GetMapping
  @PreAuthorize("hasRole('ADMIN') or hasRole('COMPANY')")
  public ResponseEntity<?> findAll() {
    try {
      List<Institutions> institutions = service.findAll();
      return ResponseEntity.ok(institutions);
    } catch (Exception e) {
      e.printStackTrace();
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
          .body(Collections.singletonMap("message", "Erro ao buscar instituições."));
    }
  }

  @GetMapping("/{id}")
  @PreAuthorize("hasRole('ADMIN')")
  public ResponseEntity<?> findById(@PathVariable("id") Integer id) {
    if (id == null || id <= 0) {
      return ResponseEntity.badRequest()
          .body(Collections.singletonMap("message", "ID inválido"));
    }
    try {
      Institutions institution = service.findById(id);
      return ResponseEntity.ok(institution);
    } catch (Exception e) {
      e.printStackTrace();
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
          .body(Collections.singletonMap("message", "Erro ao buscar instituição."));
    }
  }

  @PutMapping("/{id}")
  @PreAuthorize("hasRole('ADMIN')")
  public ResponseEntity<?> update(@PathVariable("id") Integer id, @RequestBody InstitutionsDto dto) {
    if (id == null || id <= 0) {
      return ResponseEntity.badRequest()
          .body(Collections.singletonMap("message", "ID inválido"));
    }
    if (dto == null || dto.getName() == null || dto.getName().isBlank() /* ajuste validação */) {
      return ResponseEntity.badRequest()
          .body(Collections.singletonMap("message", "Dados da instituição inválidos ou incompletos"));
    }
    try {
      service.update(id, dto);
      return ResponseEntity.ok(Collections.singletonMap("message", "Instituição atualizada com sucesso"));
    } catch (Exception e) {
      e.printStackTrace();
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
          .body(Collections.singletonMap("message", "Erro ao atualizar instituição."));
    }
  }

  @DeleteMapping("/{id}")
  @PreAuthorize("hasRole('ADMIN')")
  public ResponseEntity<?> delete(@PathVariable("id") Integer id) {
    if (id == null || id <= 0) {
      return ResponseEntity.badRequest()
          .body(Collections.singletonMap("message", "ID inválido"));
    }
    try {
      service.delete(id);
      return ResponseEntity.ok(Collections.singletonMap("message", "Instituição deletada com sucesso"));
    } catch (DataIntegrityViolationException ex) {
      return ResponseEntity.status(HttpStatus.CONFLICT)
          .body(Collections.singletonMap("message", "Não é possível excluir a Instituição, pois ela está vinculada a uma inscrição."));
    } catch (Exception ex) {
      ex.printStackTrace();
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
          .body(Collections.singletonMap("message", "Erro inesperado ao tentar excluir a Instituição."));
    }
  }
}
