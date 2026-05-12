package com.example.prol_educa.repository;

import com.example.prol_educa.models.PasswordResetTokenDto;
import com.example.prol_educa.models.CustomersDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PasswordResetTokenRepository extends JpaRepository<PasswordResetTokenDto, Long> {

  Optional<PasswordResetTokenDto> findByToken(String token);

  Optional<PasswordResetTokenDto> findByCustomer(CustomersDto customer);

  void deleteByCustomer(CustomersDto customer);
}
