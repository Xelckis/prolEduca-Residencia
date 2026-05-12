package com.example.prol_educa.controller;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.prol_educa.entities.Administrators;
import com.example.prol_educa.models.AdministratorsDto;
import com.example.prol_educa.service.AdministratorsService;

@RestController
@RequestMapping("/administrators")
public class AdministratorsController {

  @Autowired
  public AdministratorsService service;

  @PostMapping("/create")
  public ResponseEntity<?> create(@RequestBody AdministratorsDto dto) {
    if (dto == null || dto.getName() == null || dto.getName().isBlank() /* ou outras validações específicas */) {
      return ResponseEntity.badRequest()
          .body(Collections.singletonMap("message", "Dados do administrador inválidos ou incompletos"));
    }

    try {
      service.create(dto);
      return ResponseEntity.ok(Collections.singletonMap("message", "Cadastro do administrador realizado com sucesso!"));
    } catch (Exception e) {
      e.printStackTrace();
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
          .body(Collections.singletonMap("message", "Erro ao cadastrar administrador. Tente novamente."));
    }
  }

  @GetMapping
  public ResponseEntity<?> findAll() {
    try {
      List<Administrators> list = service.findAll();
      return ResponseEntity.ok(list);
    } catch (Exception e) {
      e.printStackTrace();
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
          .body(Collections.singletonMap("message", "Erro ao buscar administradores."));
    }
  }

  @GetMapping("/{id}")
  public ResponseEntity<?> findById(@PathVariable("id") Integer id) {
    if (id == null || id <= 0) {
      return ResponseEntity.badRequest()
          .body(Collections.singletonMap("message", "ID inválido"));
    }
    try {
      Administrators admin = service.findById(id);
      return ResponseEntity.ok(admin);
    } catch (Exception e) {
      e.printStackTrace();
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
          .body(Collections.singletonMap("message", "Erro ao buscar administrador."));
    }
  }

  @PutMapping("/{id}")
  public ResponseEntity<?> update(@PathVariable("id") Integer id, @RequestBody AdministratorsDto dto) {
    if (id == null || id <= 0) {
      return ResponseEntity.badRequest()
          .body(Collections.singletonMap("message", "ID inválido"));
    }
    if (dto == null || dto.getName() == null || dto.getName().isBlank() /* validação */) {
      return ResponseEntity.badRequest()
          .body(Collections.singletonMap("message", "Dados do administrador inválidos ou incompletos"));
    }

    try {
      service.update(id, dto);
      return ResponseEntity.ok(Collections.singletonMap("message", "Administrador atualizado com sucesso"));
    } catch (Exception e) {
      e.printStackTrace();
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
          .body(Collections.singletonMap("message", "Erro ao atualizar administrador."));
    }
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<?> delete(@PathVariable("id") Integer id) {
    if (id == null || id <= 0) {
      return ResponseEntity.badRequest()
          .body(Collections.singletonMap("message", "ID inválido"));
    }
    try {
      service.delete(id);
      return ResponseEntity.ok(Collections.singletonMap("message", "Administrador deletado com sucesso"));
    } catch (Exception e) {
      e.printStackTrace();
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
          .body(Collections.singletonMap("message", "Erro ao deletar administrador."));
    }
  }

}
