package com.example.prol_educa.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.prol_educa.entities.Companies;
import com.example.prol_educa.entities.Customers;
import com.example.prol_educa.models.JwtResponse;
import com.example.prol_educa.models.LoginRequestDto;
import com.example.prol_educa.repository.CompaniesRepository;
import com.example.prol_educa.repository.CustomersRepository;
import com.example.prol_educa.repository.PasswordResetTokenRepository;
import com.example.prol_educa.security.jwt.JwtUtils;
import com.example.prol_educa.security.services.UserDetailsImpl;

@Service
public class AuthService {

  @Autowired
  private AuthenticationManager authenticationManager;

  @Autowired
  private CustomersService customersService;

  @Autowired
  private CustomersRepository customersRepository;

  @Autowired
  private PasswordResetTokenRepository tokenRepository;

  @Autowired
  private EmailService emailService;

  @Autowired
  private CompaniesRepository companiesRepository;

  @Autowired
  private JwtUtils jwtUtils;

  private final int TEMPO_EXPIRACAO_MINUTOS = 10;

  public JwtResponse authenticateUser(LoginRequestDto dto) throws Exception {
    Authentication authentication = authenticationManager
        .authenticate(new UsernamePasswordAuthenticationToken(dto.getEmail(), dto.getPassword()));

    UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
    String jwt = jwtUtils.generateJwtToken(authentication);

    List<String> roles = userDetails.getAuthorities().stream().map(item -> item.getAuthority())
        .collect(Collectors.toList());

    int userId = 0;

    if (roles.contains("ROLE_USER")) {
      Customers customer = customersService.findByEmail(dto.getEmail());
      if (customer != null) {
        userId = customer.getId();
      }
    } else if (roles.contains("ROLE_COMPANY")) {
      Companies company = companiesRepository.findByEmail(dto.getEmail());
      if (company != null) {
        userId = company.getId();
      }
    }

    return new JwtResponse(jwt, roles, userId);
  }

  public void solicitarCodigo(String email) throws Exception {
    Customers customer = customersRepository.findByEmail(email);

    if (customer != null) {
      String codigo = String.format("%06d", new Random().nextInt(999999));
      customer.setCodigoRecuperacao(codigo);
      customer.setExpiracaoCodigo(LocalDateTime.now().plusMinutes(TEMPO_EXPIRACAO_MINUTOS));

      customersRepository.save(customer);
      emailService.enviarCodigoRecuperacao(email, codigo);
    } else {
      throw new Exception("Cliente não encontrado! Verifique o e-mail e tente novamente.");
    }
  }

  public void validarCodigo(String email, String codigo) {
    Customers customer = customersRepository.findByEmail(email);

    if (customer == null) {
      throw new RuntimeException("Código inválido.");
    }

    if (!codigo.equals(customer.getCodigoRecuperacao()) || customer.getExpiracaoCodigo() == null
        || customer.getExpiracaoCodigo().isBefore(LocalDateTime.now())) {
      throw new RuntimeException("Código inválido ou expirado.");
    }
  }

  public void redefinirSenha(String email, String novaSenha) {
    Customers customer = customersRepository.findByEmail(email);

    if (customer == null) {
      throw new RuntimeException("Cliente não encontrado.");
    }

    if (novaSenha.length() < 8) {
      throw new RuntimeException("A senha deve ter pelo menos 8 caracteres.");
    }

    String hash = new BCryptPasswordEncoder().encode(novaSenha);
    customer.setPassword(hash);

    customer.setCodigoRecuperacao(null);
    customer.setExpiracaoCodigo(null);

    customersRepository.save(customer);
  }

}
