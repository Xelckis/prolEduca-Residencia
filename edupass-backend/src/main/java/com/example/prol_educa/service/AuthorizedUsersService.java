package com.example.prol_educa.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.prol_educa.entities.Companies;
import com.example.prol_educa.entities.AuthorizedUsers;
import com.example.prol_educa.entities.Roles;
import com.example.prol_educa.models.AuthorizedUsersDto;
import com.example.prol_educa.repository.CompaniesRepository;
import com.example.prol_educa.repository.AuthorizedUsersRepository;
import com.example.prol_educa.repository.RolesRepository;
import com.example.prol_educa.utils.enuns.ERoles;

@Service
public class AuthorizedUsersService {

  @Autowired
  public AuthorizedUsersRepository repository;

  @Autowired
  public RolesRepository rolesRepository;

  @Autowired
  public CompaniesRepository companiesRepository;

  @Autowired
  PasswordEncoder passwordEncoder;

  public void create(AuthorizedUsersDto dto) {

    Companies company = companiesRepository.findById(dto.getEmpresaId())
      .orElseThrow(() -> new RuntimeException("Erro: Empresa não encontrada."));

    AuthorizedUsers AuthorizedUsers = new AuthorizedUsers();

    // Dados pessoais
    AuthorizedUsers.setName(dto.getName());
    AuthorizedUsers.setCpf(dto.getCpf());
    AuthorizedUsers.setRg(dto.getRg());
    AuthorizedUsers.setEmail(dto.getEmail());
    AuthorizedUsers.setPhone(dto.getPhone());
    AuthorizedUsers.setEstado(dto.getEstado());
    AuthorizedUsers.setCidade(dto.getCidade());
    AuthorizedUsers.setBairro(dto.getBairro());
    AuthorizedUsers.setEmpresa(company);

    Roles userRoles = rolesRepository.findByType(ERoles.ROLE_USER);
    if (userRoles == null) {
      throw new RuntimeException("Error: Role USER not found.");
    }

    AuthorizedUsers.getRoles().add(userRoles);
    repository.save(AuthorizedUsers);
  }

  public AuthorizedUsers findById(Integer id) throws Exception {
    Optional<AuthorizedUsers> AuthorizedUsers = repository.findById(id);
    if (!AuthorizedUsers.isPresent()) {
      throw new Exception("Cliente não encontrado");
    }

    return AuthorizedUsers.get();
  }

  public List<AuthorizedUsers> findAll() {
    return repository.findAll();
  }

  public AuthorizedUsers update(Integer id, AuthorizedUsersDto dto) throws Exception {
    AuthorizedUsers AuthorizedUsers = findById(id);

    // Verifica se a empresa existe
    Companies company = companiesRepository.findById(dto.getEmpresaId())
        .orElseThrow(() -> new RuntimeException("Erro: Empresa não encontrada."));

    // Dados pessoais
    AuthorizedUsers.setName(dto.getName());
    AuthorizedUsers.setCpf(dto.getCpf());
    AuthorizedUsers.setRg(dto.getRg());
    AuthorizedUsers.setEmail(dto.getEmail());
    AuthorizedUsers.setPhone(dto.getPhone());
    AuthorizedUsers.setEstado(dto.getEstado());
    AuthorizedUsers.setCidade(dto.getCidade());
    AuthorizedUsers.setBairro(dto.getBairro());
    AuthorizedUsers.setEmpresa(company);

    return repository.save(AuthorizedUsers);
  }

  public void delete(Integer id) {
    repository.deleteById(id);
  }

  public AuthorizedUsers findByEmail(String email) {
    return repository.findByEmail(email);
  }

  public AuthorizedUsers findByCpf(String cpf) {
    return repository.findByCpf(cpf);
  }

  public List<AuthorizedUsers> findByEmpresaId(Integer empresaId) {
    return repository.findByEmpresa_Id(empresaId);
  }

}
