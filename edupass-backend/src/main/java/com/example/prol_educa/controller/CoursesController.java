package com.example.prol_educa.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.example.prol_educa.entities.Courses;
import com.example.prol_educa.models.CoursesDto;
import com.example.prol_educa.service.CoursesService;

@RestController
@RequestMapping("/courses")
public class CoursesController {

  @Autowired
  public CoursesService service;

  @PostMapping("/create")
  @PreAuthorize("hasRole('ADMIN')")
  public ResponseEntity<?> create(@RequestBody CoursesDto dto) {
    if (dto == null || dto.getName() == null || dto.getName().isBlank() /* adicionar outras validações se necessário */) {
      return ResponseEntity.badRequest()
          .body(Collections.singletonMap("message", "Dados do curso inválidos ou incompletos"));
    }

    try {
      service.create(dto);
      return ResponseEntity.ok(Collections.singletonMap("message", "Cadastro do curso feito com sucesso"));
    } catch (Exception e) {
      e.printStackTrace();
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
          .body(Collections.singletonMap("message", "Erro ao cadastrar curso. Tente novamente."));
    }
  }

  // @GetMapping
  // public ResponseEntity<List<Courses>> findAll(){
  // return ResponseEntity.ok(service.findAll());
  // }

  // Buscar somente os cursos que estão com vagas
  @GetMapping
  public ResponseEntity<?> findAllFromVacancies() {
    try {
      // List<Courses> courses = service.findAllFromVacancies(0);
      List<Courses> courses = service.findAll();
      return ResponseEntity.ok(courses);
    } catch (Exception e) {
      e.printStackTrace();
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
          .body(Collections.singletonMap("message", "Erro ao buscar cursos com vagas."));
    }
  }

  @GetMapping("/{id}")
  public ResponseEntity<?> findById(@PathVariable("id") Integer id) {
    if (id == null || id <= 0) {
      return ResponseEntity.badRequest()
          .body(Collections.singletonMap("message", "ID inválido"));
    }

    try {
      Courses course = service.findById(id);
      return ResponseEntity.ok(course);
    } catch (Exception e) {
      e.printStackTrace();
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
          .body(Collections.singletonMap("message", "Erro ao buscar curso."));
    }
  }

  @PutMapping("/{id}")
  @PreAuthorize("hasRole('ADMIN')")
  public ResponseEntity<?> update(@PathVariable("id") Integer id, @RequestBody CoursesDto dto) {
    if (id == null || id <= 0) {
      return ResponseEntity.badRequest()
          .body(Collections.singletonMap("message", "ID inválido"));
    }
    if (dto == null || dto.getName() == null || dto.getName().isBlank() /* outras validações */) {
      return ResponseEntity.badRequest()
          .body(Collections.singletonMap("message", "Dados do curso inválidos ou incompletos"));
    }

    try {
      service.update(id, dto);
      return ResponseEntity.ok(Collections.singletonMap("message", "Curso atualizado com sucesso"));
    } catch (Exception e) {
      e.printStackTrace();
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
          .body(Collections.singletonMap("message", "Erro ao atualizar curso."));
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

  // cliente
  // @GetMapping("/findByName")
  // @PreAuthorize("hasRole('ADMIN', 'USER')")
  // public ResponseEntity<?> findByName(@RequestParam("name") String name){
  // return ResponseEntity.ok(service.findByName(name));
  // }

  // @GetMapping("/findByInstitution")
  // public ResponseEntity<?> findByInstitution(@RequestParam("institutionName")
  // String institutionName){
  // return ResponseEntity.ok(service.findByInstituitions_Name(institutionName));
  // }

  // @GetMapping("/findByPercentageScholarship")
  // public ResponseEntity<?>
  // findByPercentageScholarship(@RequestParam("percentageScholarship") BigDecimal
  // percentageScholarship){
  // return
  // ResponseEntity.ok(service.findByPercentageScholarship(percentageScholarship));
  // }

  // cliente
  // adicioonar cidade e categoria
  @GetMapping("/filter")
  public ResponseEntity<?> findByFilter(@RequestParam(required = false) String name,
      @RequestParam(required = false) String institutions, @RequestParam(required = false) String shift,
      @RequestParam(required = false) String minPercentageScholarship, @RequestParam(required = false) String city,
      @RequestParam(required = false) String category) {
    Map<String, String> filters = new HashMap<>();

    if (name != null)
      filters.put("name", name);
    if (institutions != null)
      filters.put("institutions", institutions);
    if (shift != null)
      filters.put("shift", shift);
    if (minPercentageScholarship != null)
      filters.put("minPercentageScholarship", minPercentageScholarship);
    if (city != null)
      filters.put("city", city);
    if (category != null)
      filters.put("category", category);

    try {
      List<Courses> result = service.filter(filters);
      return ResponseEntity.ok(result);
    } catch (Exception e) {
      e.printStackTrace();
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
          .body(Collections.singletonMap("message", "Erro ao filtrar cursos."));
    }
  }

}
