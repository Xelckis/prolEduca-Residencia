package com.example.prol_educa.controller;

import java.util.List;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.example.prol_educa.entities.Customers;
import com.example.prol_educa.entities.Registrations;
import com.example.prol_educa.entities.Customers;
import com.example.prol_educa.models.RegistrationsDto;
import com.example.prol_educa.repository.CustomersRepository;
import com.example.prol_educa.service.EmailService;
import com.example.prol_educa.service.RegistrationsService;
import com.example.prol_educa.service.EmailService;
import com.example.prol_educa.repository.CustomersRepository;

@RestController
@RequestMapping("/registrations")
public class RegistrationsController {
  
  @Autowired
  public RegistrationsService service;

  @Autowired
  public EmailService EmailService;

  @Autowired
  public CustomersRepository CustomersRepository;

  @PostMapping("/create")
  public ResponseEntity<?> create(@RequestBody RegistrationsDto dto) {
    if (dto == null /* Pode adicionar validações específicas aqui, se desejar */) {
      return ResponseEntity.badRequest()
          .body(Collections.singletonMap("message", "Dados do registro inválidos ou incompletos"));
    }
    try {
      service.create(dto);
      return ResponseEntity.ok(Collections.singletonMap("message", "Registro criado com sucesso"));
    } catch (Exception e) {
      e.printStackTrace();
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
          .body(Collections.singletonMap("message", "Erro ao criar registro. Tente novamente."));
    }
  }

  @GetMapping
  public ResponseEntity<?> findAll() {
    try {
      List<Registrations> registrations = service.findAll();
      return ResponseEntity.ok(registrations);
    } catch (Exception e) {
      e.printStackTrace();
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
          .body(Collections.singletonMap("message", "Erro ao buscar registros."));
    }
  }

  @GetMapping("/{id}")
  public ResponseEntity<?> findById(@PathVariable("id") Integer id) {
    if (id == null || id <= 0) {
      return ResponseEntity.badRequest()
          .body(Collections.singletonMap("message", "ID inválido"));
    }

    try {
      Registrations registration = service.findById(id);
      return ResponseEntity.ok(registration);
    } catch (Exception e) {
      e.printStackTrace();
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
          .body(Collections.singletonMap("message", "Erro ao buscar registro."));
    }
  }

  @GetMapping("/company/{id}")
  @PreAuthorize("hasRole('COMPANY')")
  public ResponseEntity<?> findByCompany(@PathVariable("id") Integer id) {
    if (id == null || id <= 0) {
      return ResponseEntity.badRequest()
          .body(Collections.singletonMap("message", "ID inválido"));
    }

    try {
      List<Registrations> registrations = service.findAllByCompanyId(id);
      return ResponseEntity.ok(registrations);
    } catch (Exception e) {
      e.printStackTrace();
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
          .body(Collections.singletonMap("message", "Erro ao buscar registros da empresa."));
    }
  }

  @PutMapping("/{id}")
  public ResponseEntity<?> update(@PathVariable("id") Integer id, @RequestBody RegistrationsDto dto) {
    if (id == null || id <= 0) {
      return ResponseEntity.badRequest()
          .body(Collections.singletonMap("message", "ID inválido"));
    }
    if (dto == null /* Pode adicionar validações específicas aqui, se desejar */) {
      return ResponseEntity.badRequest()
          .body(Collections.singletonMap("message", "Dados do registro inválidos ou incompletos"));
    }

    try {
      service.update(id, dto);
      return ResponseEntity.ok(Collections.singletonMap("message", "Registro atualizado com sucesso"));
    } catch (Exception e) {
      e.printStackTrace();
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
          .body(Collections.singletonMap("message", "Erro ao atualizar registro."));
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
      return ResponseEntity.ok(Collections.singletonMap("message", "Registro deletado com sucesso"));
    } catch (Exception e) {
      e.printStackTrace();
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
          .body(Collections.singletonMap("message", "Erro ao deletar registro."));
    }
  }

  @PostMapping("/send-email/{customerId}")
  public ResponseEntity<?> sendEmailToCustomer(@PathVariable Integer customerId) {
    try {
        Customers customer = CustomersRepository.findById(customerId)

            .orElseThrow(() -> new IllegalArgumentException("Cliente com ID " + customerId + " não encontrado."));

        String email = customer.getEmail();
        String name = customer.getFullName();

        if (email == null || email.isBlank()) {
            return ResponseEntity.badRequest().body("O cliente não possui um e-mail cadastrado.");
        }
      
        EmailService emailService = new EmailService();
        emailService.sendEmail(email, "Sua vaga foi confirmada!", """
        Olá %s, 

        Temos uma ótima notícia! A vaga que você estava aguardando foi confirmada para você. 🎉
        Agradecemos pela sua paciência durante o processo e estamos felizes em informar que você conseguiu a vaga.
        Para mais informações sobre o curso/serviço, acesse sua conta ou entre em contato conosco.
        """.formatted(name));

        return ResponseEntity.ok("E-mail enviado com sucesso para: " + email);

    } catch (IllegalArgumentException e) {
        // Erro de cliente não encontrado
        return ResponseEntity.status(404).body(e.getMessage());
    } catch (Exception e) {
        // Qualquer outro erro (ex: falha no servidor de e-mail)
        return ResponseEntity.status(500).body("Erro ao enviar o e-mail: " + e.getMessage());
    }
  }
}
