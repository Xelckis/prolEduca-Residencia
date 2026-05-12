package com.example.prol_educa.controller;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.prol_educa.models.EmailDto;
import com.example.prol_educa.models.JwtResponse;
import com.example.prol_educa.models.LoginRequestDto;
import com.example.prol_educa.models.ResetPasswordDto;
import com.example.prol_educa.models.ValidateCodeDto;
import com.example.prol_educa.service.AuthService;

@RestController
@RequestMapping("/edupass")
public class AuthController {

  @Autowired
  private AuthService authService;

  @PostMapping("/login")
  public ResponseEntity<?> signin(@RequestBody LoginRequestDto dto) {
    if (dto == null || dto.getEmail() == null || dto.getEmail().isBlank() 
        || dto.getPassword() == null || dto.getPassword().isBlank()) {
      return ResponseEntity.badRequest().body(Collections.singletonMap("message", "Email ou senha inválidos"));
    }

    try {
      JwtResponse jwtResponse = authService.authenticateUser(dto);
      return ResponseEntity.ok(jwtResponse);
    } catch (BadCredentialsException e) {
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
          .body(Collections.singletonMap("message", "Email ou senha incorretos"));
    } catch (Exception e) {
      e.printStackTrace();
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
          .body(Collections.singletonMap("message", "Erro ao tentar autenticar"));
    }
  }

  @PostMapping("/forgot-password")
  public ResponseEntity<?> forgotPassword(@RequestBody EmailDto request) {
    if (request == null || request.getEmail() == null || request.getEmail().isBlank()) {
      return ResponseEntity.badRequest().body(Collections.singletonMap("message", "Email é obrigatório"));
    }

    try {
      authService.solicitarCodigo(request.getEmail());
      return ResponseEntity.ok(Collections.singletonMap("message", "Se o email estiver cadastrado, o código foi enviado."));
    } catch (Exception e) {
      e.printStackTrace();
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
          .body(Collections.singletonMap("message", e.getMessage()));
    }
  }

  @PostMapping("/validate-code")
  public ResponseEntity<?> validateCode(@RequestBody ValidateCodeDto request) {
    if (request == null || request.getEmail() == null || request.getEmail().isBlank()
        || request.getCode() == null || request.getCode().isBlank()) {
      return ResponseEntity.badRequest().body(Collections.singletonMap("message", "Email e código são obrigatórios"));
    }

    try {
      authService.validarCodigo(request.getEmail(), request.getCode());
      return ResponseEntity.ok().build();
    } catch (IllegalArgumentException e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST)
          .body(Collections.singletonMap("message", e.getMessage()));
    } catch (Exception e) {
      e.printStackTrace();
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
          .body(Collections.singletonMap("message", "Erro ao validar o código, digite o código corretamente"));
    }
  }

  @PostMapping("/reset-password")
  public ResponseEntity<?> resetPassword(@RequestBody ResetPasswordDto request) {
    if (request == null || request.getEmail() == null || request.getEmail().isBlank()
        || request.getPassword() == null || request.getPassword().isBlank()) {
      return ResponseEntity.badRequest().body(Collections.singletonMap("message", "Email e nova senha são obrigatórios"));
    }

    try {
      authService.redefinirSenha(request.getEmail(), request.getPassword());
      return ResponseEntity.ok(Collections.singletonMap("message", "Senha redefinida com sucesso."));
    } catch (IllegalArgumentException e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST)
          .body(Collections.singletonMap("message", e.getMessage()));
    } catch (Exception e) {
      e.printStackTrace();
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
          .body(Collections.singletonMap("message", "Erro ao redefinir a senha. Tente novamente."));
    }
  }
}