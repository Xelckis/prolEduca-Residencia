package com.example.prol_educa.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.prol_educa.entities.Companies;
import com.example.prol_educa.entities.Customers;
import com.example.prol_educa.entities.AuthorizedUsers;
import com.example.prol_educa.entities.Roles;
import com.example.prol_educa.models.CustomersDto;
import com.example.prol_educa.repository.CompaniesRepository;
import com.example.prol_educa.repository.CustomersRepository;
import com.example.prol_educa.repository.AuthorizedUsersRepository;
import com.example.prol_educa.repository.RolesRepository;
import com.example.prol_educa.utils.enuns.ERoles;

@Service
public class CustomersService {

  @Autowired
  public CustomersRepository repository;

  @Autowired
  public AuthorizedUsersRepository AuthorizedUsersRepository;

  @Autowired
  public RolesRepository rolesRepository;

  @Autowired
  public CompaniesRepository companiesRepository;

  @Autowired
  PasswordEncoder passwordEncoder;

  public void create(CustomersDto dto) {

    Companies company = companiesRepository.findById(dto.getEmpresaId())
      .orElseThrow(() -> new RuntimeException("Erro: Empresa não encontrada."));

    if (dto == null 
      || dto.getEmail() == null || dto.getEmail().isBlank()
      || dto.getCpf() == null || dto.getCpf().isBlank()) {
      throw new RuntimeException("Dados do cliente inválidos ou incompletos");
    }

    // AuthorizedUsers autorizadoEmail = AuthorizedUsersRepository.findByEmailAndEmpresa_Id(dto.getEmail(), dto.getEmpresaId());
    //   if (autorizadoEmail == null) {
    //     throw new RuntimeException("O email informado não está autorizado para cadastro nesta empresa. Por favor, verifique se o email e a empresa selecionada estão corretos.");
    //   }

    AuthorizedUsers autorizadoCpf = AuthorizedUsersRepository.findByCpf(dto.getCpf().replaceAll("\\D", ""));
      if (autorizadoCpf == null) {
        throw new RuntimeException("CPF não autorizado para cadastro!");
      }

    if (repository.findByEmail(dto.getEmail()) != null) {
    throw new RuntimeException("Email já está em uso");
    }

    // Verifica se CPF já existe
    if (repository.findByCpf(dto.getCpf()) != null) {
        throw new RuntimeException("CPF já está cadastrado");
    }

    Customers customer = new Customers();

    // Dados pessoais
    customer.setFullName(dto.getFullName());
    customer.setEmail(dto.getEmail());
    customer.setPhone(dto.getPhone());
    customer.setCpf(dto.getCpf());
    customer.setDateOfBirth(dto.getDateOfBirth());
    customer.setStatus(dto.isStatus());
    customer.setPassword(passwordEncoder.encode(dto.getPassword()));

    // Endereço
    customer.setCep(dto.getCep());
    customer.setLogradouro(dto.getLogradouro());
    customer.setNumero(dto.getNumero());
    customer.setComplemento(dto.getComplemento());
    customer.setBairro(dto.getBairro());
    customer.setCidade(dto.getCidade());
    customer.setEstado(dto.getEstado());

    // Empresa
    customer.setEmpresa(company);
    // customer.setCnpj(dto.getCnpj());
    customer.setCargo(dto.getCargo());
    customer.setSetor(dto.getSetor());
    customer.setEmailCorporativo(dto.getEmail_corporativo());
    customer.setTelefoneComercial(dto.getTelefone_comercial());

    Roles userRoles = rolesRepository.findByType(ERoles.ROLE_USER);
    if (userRoles == null) {
      throw new RuntimeException("Error: Role USER not found.");
    }

    customer.getRoles().add(userRoles);
    repository.save(customer);
  }

  public Customers findById(Integer id) throws Exception {
    Optional<Customers> customer = repository.findById(id);
    if (!customer.isPresent()) {
      throw new Exception("Cliente não encontrado");
    }

    return customer.get();
  }

  public List<Customers> findAll() {
    return repository.findAll();
  }

  public Customers update(Integer id, CustomersDto dto) throws Exception {
    Customers customer = findById(id);

    // Verifica se a empresa existe
    // Companies company = companiesRepository.findById(dto.getEmpresaId())
    //     .orElseThrow(() -> new RuntimeException("Erro: Empresa não encontrada."));

    // Dados Pessoais
    customer.setFullName(dto.getFullName());
    customer.setPhone(dto.getPhone());
    customer.setDateOfBirth(dto.getDateOfBirth());
    // customer.setEmail(dto.getEmail());
    // customer.setCpf(dto.getCpf());
    // customer.setStatus(dto.isStatus());

    // Endereço
    // customer.setCep(dto.getCep());
    // customer.setLogradouro(dto.getLogradouro());
    // customer.setNumero(dto.getNumero());
    // customer.setComplemento(dto.getComplemento());
    // customer.setBairro(dto.getBairro());
    // customer.setCidade(dto.getCidade());
    // customer.setEstado(dto.getEstado());

    // Empresa
    // customer.setEmpresa(company);
    // customer.setCargo(dto.getCargo());
    // customer.setSetor(dto.getSetor());
    // customer.setEmailCorporativo(dto.getEmail_corporativo());
    // customer.setTelefoneComercial(dto.getTelefone_comercial());

    return repository.save(customer);
  }

  public void delete(Integer id) {
    repository.deleteById(id);
  }

  public Customers findByEmail(String email) {
    return repository.findByEmail(email);
  }

  public Customers findByCpf(String cpf) {
    return repository.findByCpf(cpf);
  }

  public List<Customers> findByCompanyId(Integer empresaId) {
    return repository.findByEmpresaId(empresaId);
  }

}
