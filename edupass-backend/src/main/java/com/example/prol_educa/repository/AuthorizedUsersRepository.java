package com.example.prol_educa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.prol_educa.entities.AuthorizedUsers;

@Repository
public interface AuthorizedUsersRepository extends JpaRepository<AuthorizedUsers, Integer> {
  AuthorizedUsers findByEmailAndEmpresa_Id(String email, Integer empresaId);

  AuthorizedUsers findByEmail(String email);

  AuthorizedUsers findByName(String name);

  AuthorizedUsers findByCpf(String cpf);

  List<AuthorizedUsers> findByEmpresa_Id(Integer empresaId);

}
