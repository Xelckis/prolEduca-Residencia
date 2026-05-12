package com.example.prol_educa.controller;

import java.util.List;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.example.prol_educa.entities.ScholarshipHolders;
import com.example.prol_educa.models.ScholarshipHoldersCreationResponseDTO;
import com.example.prol_educa.models.ScholarshipHoldersDto;
import com.example.prol_educa.service.ScholarshipHoldersService;

@RestController
@RequestMapping("/scholarship-holders")
public class ScholarshipHoldersController {

    @Autowired
    public ScholarshipHoldersService service;

    @PostMapping("/create")
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    public ResponseEntity<?> create(@RequestBody ScholarshipHoldersDto dto) {
        if (dto == null /* Adicione validações específicas se necessário */) {
            return ResponseEntity.badRequest()
                .body(Collections.singletonMap("message", "Dados do bolsista inválidos ou incompletos"));
        }
        try {
            ScholarshipHolders scholarshipHolder = service.create(dto);
            ScholarshipHoldersCreationResponseDTO response = new ScholarshipHoldersCreationResponseDTO(
                "Cadastro do bolsista feito com sucesso", scholarshipHolder.getId());
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(Collections.singletonMap("message", "Erro ao cadastrar bolsista. Tente novamente."));
        }
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN') or hasRole('COMPANY')")
    public ResponseEntity<?> findAll() {
        try {
            List<ScholarshipHolders> holders = service.findAll();
            return ResponseEntity.ok(holders);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(Collections.singletonMap("message", "Erro ao buscar bolsistas."));
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
            ScholarshipHolders holder = service.findById(id);
            return ResponseEntity.ok(holder);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(Collections.singletonMap("message", "Erro ao buscar bolsista."));
        }
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> update(@PathVariable("id") Integer id, @RequestBody ScholarshipHoldersDto dto) {
        if (id == null || id <= 0) {
            return ResponseEntity.badRequest()
                .body(Collections.singletonMap("message", "ID inválido"));
        }
        if (dto == null /* Adicione validações específicas se necessário */) {
            return ResponseEntity.badRequest()
                .body(Collections.singletonMap("message", "Dados do bolsista inválidos ou incompletos"));
        }
        try {
            service.update(id, dto);
            return ResponseEntity.ok(Collections.singletonMap("message", "Bolsista atualizado com sucesso"));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(Collections.singletonMap("message", "Erro ao atualizar bolsista."));
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
      return ResponseEntity.ok(Collections.singletonMap("message", "Curso deletado com sucesso"));
    } catch (DataIntegrityViolationException ex) {
      return ResponseEntity.status(HttpStatus.CONFLICT)
          .body(Collections.singletonMap("message", "Não é possível excluir o curso, pois ele está vinculado a uma inscrição."));
    } catch (Exception ex) {
      ex.printStackTrace();
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
          .body(Collections.singletonMap("message", "Erro inesperado ao tentar excluir o curso."));
    }
  }
}
