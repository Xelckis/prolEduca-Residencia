package com.example.prol_educa.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.prol_educa.entities.Companies;
import com.example.prol_educa.models.CompaniesDto;
import com.example.prol_educa.service.CompaniesService;

@RestController
@RequestMapping("/companies")
public class CompaniesController {

  @Autowired
  private CompaniesService service;

  @PostMapping("/create")
  public ResponseEntity<?> create(@RequestBody CompaniesDto dto) {
    service.create(dto);
    return ResponseEntity.ok("Cadastro da empresa realizado com sucesso!");
  }

  @GetMapping
  public ResponseEntity<List<Companies>> findAll() {
    return ResponseEntity.ok(service.findAll());
  }

  @GetMapping("/{id}")
  public ResponseEntity<Companies> findById(@PathVariable("id") Integer id) throws Exception {
    return ResponseEntity.ok(service.findById(id));
  }

  @PutMapping("/{id}")
  public ResponseEntity<?> update(@PathVariable("id") Integer id, @RequestBody CompaniesDto dto) throws Exception {
    service.update(id, dto);
    return ResponseEntity.ok("Empresa atualizada com sucesso");
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<?> delete(@PathVariable("id") Integer id) {
    service.delete(id);
    return ResponseEntity.ok("Empresa deletada com sucesso");
  }

  @GetMapping("/monthly-fee/{id}")
  public ResponseEntity<Map<String, Object>> getCompanyMonthlyFee(@PathVariable("id") Integer id) throws Exception {
    Map<String, Object> response = new HashMap<>();
    try {
      Integer monthlyFeeValue = service.getCompanyMonthlyFee(id);
      response.put("status", true);
      response.put("monthlyFeeValue", monthlyFeeValue);
    } catch (Exception e) {
      response.put("status", false);
      response.put("monthlyFeeValue", 0);
      response.put("message", e.getMessage());
    }

    return ResponseEntity.ok(response);
  }

  @GetMapping("/transactions/{id}")
  public ResponseEntity<?> getCompanyTransactions(@PathVariable("id") Integer id) throws Exception {
    return ResponseEntity.ok(service.findByEmpresaId(id));
  }

  @GetMapping("/{companyId}/savings")
  public ResponseEntity<?> getCompanySavings(@PathVariable("companyId") Integer companyId) {
    try {
      Map<String, Object> savings = service.calculateCompanySavings(companyId);
      return ResponseEntity.ok(savings);
    } catch (Exception e) {
      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }
}
