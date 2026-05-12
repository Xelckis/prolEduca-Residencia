package com.example.prol_educa.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.prol_educa.entities.Companies;
import com.example.prol_educa.entities.Courses;
import com.example.prol_educa.entities.Customers;
import com.example.prol_educa.entities.Registrations;
import com.example.prol_educa.entities.Roles;
import com.example.prol_educa.entities.Transactions;
import com.example.prol_educa.models.CompaniesDto;
import com.example.prol_educa.repository.CompaniesRepository;
import com.example.prol_educa.repository.RegistrationsRepository;
import com.example.prol_educa.repository.RolesRepository;
import com.example.prol_educa.repository.TransactionsRepository;
import com.example.prol_educa.utils.enuns.ERoles;

@Service
public class CompaniesService {

  @Autowired
  private CompaniesRepository repository;

  @Autowired
  private TransactionsRepository transactionsRepository;

  @Autowired
  private CustomersService customersService;

  @Autowired
  private PasswordEncoder passwordEncoder;

  @Autowired
  private RolesRepository rolesRepository;

  @Autowired
  private RegistrationsRepository registrationsRepository;

  public void create(CompaniesDto dto) {
    Companies company = new Companies();

    company.setEmail(dto.getEmail());
    company.setPassword(passwordEncoder.encode(dto.getPassword()));
    company.setCnpj(dto.getCnpj());
    company.setFantasyName(dto.getFantasyName());
    company.setPhoneNumber(dto.getPhoneNumber());

    Roles companyRole = rolesRepository.findByType(ERoles.ROLE_COMPANY);

    if (companyRole == null)
      throw new RuntimeException("Error: Role de empresas não encontrada.");

    company.getRoles().add(companyRole);

    repository.save(company);
  }

  public List<Companies> findAll() {
    return repository.findAll();
  }

  public Companies findById(Integer id) throws Exception {
    Optional<Companies> company = repository.findById(id);
    if (!company.isPresent()) {
      throw new Exception("Empresa não encontrada");
    }

    return company.get();
  }

  public Companies update(Integer id, CompaniesDto dto) throws Exception {
    Companies company = findById(id);

    company.setEmail(dto.getEmail());
    company.setPassword(passwordEncoder.encode(dto.getPassword())); // re-encode the password
    company.setCnpj(dto.getCnpj());
    company.setFantasyName(dto.getFantasyName());
    company.setPhoneNumber(dto.getPhoneNumber());

    return repository.save(company);
  }

  public void delete(Integer id) {
    repository.deleteById(id);
  }

  public Integer getCompanyMonthlyFee(Integer companyId) throws Exception {
    Optional<Companies> company = repository.findById(companyId);
    if (!company.isPresent()) {
      throw new Exception("Empresa não encontrada");
    }

    List<Customers> companyCustomers = customersService.findByCompanyId(companyId);
    Integer monthflyFee = companyCustomers.size() * 20;

    return monthflyFee;
  }

  public List<Transactions> findByEmpresaId(Integer id) {
    List<Transactions> getAllTransactions = transactionsRepository.findByEmpresaId_Id(id);
    return getAllTransactions;
  }

  public Map<String, Object> calculateCompanySavings(Integer companyId) throws Exception {
    Companies company = findById(companyId);

    List<Registrations> registrations = registrationsRepository.findAllByCompanyId(companyId);

    // if (registrations.isEmpty()) {
    // throw new Exception("Nenhum aluno vinculado a esta empresa.");
    // }
    if (registrations.isEmpty()) {
      Map<String, Object> result = new HashMap<>();
      result.put("companyId", company.getId());
      result.put("companyName", company.getFantasyName());
      result.put("totalRegistrations", 0);
      result.put("totalOriginal", BigDecimal.ZERO.setScale(2));
      result.put("totalWithDiscount", BigDecimal.ZERO.setScale(2));
      result.put("savings", BigDecimal.ZERO.setScale(2));
      return result;
    }

    BigDecimal totalOriginal = BigDecimal.ZERO;
    BigDecimal totalWithDiscount = BigDecimal.ZERO;

    for (Registrations reg : registrations) {
      Courses course = reg.getCourses();
      // BigDecimal originalValue = course.getOriginalValue();
      BigDecimal originalValue = course.getOriginalValue() != null ? course.getOriginalValue() : BigDecimal.ZERO;
      BigDecimal percentageScholarship = course.getPercentageScholarship() != null ? course.getPercentageScholarship() : BigDecimal.ZERO;

      BigDecimal discountAmount = originalValue.multiply(percentageScholarship).divide(BigDecimal.valueOf(100), 2,
          RoundingMode.HALF_UP);
      BigDecimal discountedValue = originalValue.subtract(discountAmount);

      totalOriginal = totalOriginal.add(originalValue.multiply(BigDecimal.valueOf(12)));
      totalWithDiscount = totalWithDiscount.add(discountedValue.multiply(BigDecimal.valueOf(11)));
    }

    BigDecimal savings = totalOriginal.subtract(totalWithDiscount);

    Map<String, Object> result = new HashMap<>();
    result.put("companyId", company.getId());
    result.put("companyName", company.getFantasyName());
    result.put("totalRegistrations", registrations.size());
    result.put("totalOriginal", totalOriginal.setScale(2, RoundingMode.HALF_UP));
    result.put("totalWithDiscount", totalWithDiscount.setScale(2, RoundingMode.HALF_UP));
    result.put("savings", savings.setScale(2, RoundingMode.HALF_UP));

    return result;
  }
}
