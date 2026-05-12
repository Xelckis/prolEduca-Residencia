package com.example.prol_educa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.prol_educa.models.RequestSupportDto;
import com.example.prol_educa.service.EmailService;

@RestController
@RequestMapping("/support")
public class SupportController {

  @Autowired
  private EmailService emailService;

  @PostMapping
  public ResponseEntity<?> sendSupportEmail(@RequestBody RequestSupportDto request) {
    try {
      emailService.sendSupportRequest(request);
      return ResponseEntity.ok("Solicitação de suporte enviada com sucesso!");

    } catch (IllegalArgumentException e) {
      return ResponseEntity.badRequest().body("Erro de validação: " + e.getMessage());

    } catch (Exception e) {
      return ResponseEntity.status(500).body("Erro inesperado ao enviar email: " + e.getMessage());
    }
  }

}
