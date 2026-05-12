package com.example.prol_educa.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.prol_educa.entities.Administrators;
import com.example.prol_educa.entities.Roles;
import com.example.prol_educa.models.AdministratorsDto;
import com.example.prol_educa.repository.AdministratorsRepository;
import com.example.prol_educa.repository.RolesRepository;
import com.example.prol_educa.utils.enuns.ERoles;

@Service
public class AdministratorsService {

  @Autowired
  public AdministratorsRepository repository;

  @Autowired
  PasswordEncoder passwordEncoder;

  @Autowired
  public RolesRepository rolesRepository;

  public void create(AdministratorsDto dto) {
    Administrators administrator = new Administrators();
    administrator.setName(dto.getName());
    administrator.setEmail(dto.getEmail());
    administrator.setPassword(passwordEncoder.encode(dto.getPassword()));

    Roles adminRole = rolesRepository.findByType(ERoles.ROLE_ADMIN);
    if (adminRole == null)
      throw new RuntimeException("Error: Role ADMIN not found.");

    administrator.getRoles().add(adminRole);

    repository.save(administrator);
  }

  public List<Administrators> findAll() {
    return repository.findAll();
  }

  public Administrators findById(Integer id) throws Exception {
    Optional<Administrators> administrator = repository.findById(id);
    if (!administrator.isPresent()) {
      throw new Exception("Administrador nao encontrado");
    }

    return administrator.get();
  }

  public Administrators update(Integer id, AdministratorsDto dto) throws Exception {
    Administrators administrator = findById(id);
    administrator.setName(dto.getName());
    administrator.setEmail(dto.getEmail());
    administrator.setPassword(dto.getPassword());

    return repository.save(administrator);
  }

  public void delete(Integer id) {
    repository.deleteById(id);
  }
}
