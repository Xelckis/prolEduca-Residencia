package com.example.prol_educa.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.prol_educa.entities.Courses;
import com.example.prol_educa.entities.Registrations;
import com.example.prol_educa.entities.ScholarshipHolders;
import com.example.prol_educa.entities.StatusRegistrations;
import com.example.prol_educa.models.RegistrationsDto;
import com.example.prol_educa.repository.RegistrationsRepository;
import com.example.prol_educa.repository.StatusRegistrationsRepository;

@Service
public class RegistrationsService {

  @Autowired
  public RegistrationsRepository repository;

  @Autowired
  public StatusRegistrationsRepository statusRegistrationsRepository;

  @Autowired
  public ScholarshipHoldersService scholarshipHoldersService;

  @Autowired
  public CoursesService coursesService;

  public void create(RegistrationsDto dto) throws Exception {
    Registrations registrations = new Registrations();

    ScholarshipHolders scholarshipHolder = scholarshipHoldersService.findById(dto.getScholarshipHolderId());
    Courses course = coursesService.findById(dto.getCourseId());

    registrations.setRegistrationDate(dto.getRegistrationDate());
    registrations.setScholarshipHolders(scholarshipHolder);
    registrations.setCourses(course);

    Integer vacancies = course.getVacancies();

    if (vacancies != null && vacancies > 0) {
        // Tem vaga → status 2
        StatusRegistrations statusMatriculado = statusRegistrationsRepository.findById(2);
        if (statusMatriculado == null) {
            throw new RuntimeException("Status 'Matriculado' (id=2) não encontrado.");
        }
        registrations.setStatus(statusMatriculado);

        repository.save(registrations);
        coursesService.reduceVacancies(course.getId());
    } else {
        // Sem vaga → status 1
        StatusRegistrations statusPendente = statusRegistrationsRepository.findById(1);
        if (statusPendente == null) {
            throw new RuntimeException("Status 'Pendente' (id=1) não encontrado.");
        }
        registrations.setStatus(statusPendente);

        repository.save(registrations);
    }
  }



  public List<Registrations> findAll() {
    return repository.findAll();
  }

  public List<Registrations> findAllByCompanyId(Integer companyId) {
    return repository.findAllByCompanyId(companyId);
  }

  public Registrations findById(Integer id) throws Exception {
    Optional<Registrations> registration = repository.findById(id);
    if (!registration.isPresent()) {
      throw new Exception("Inscrição não encontrada");
    }

    return registration.get();
  }

  public Registrations update(Integer id, RegistrationsDto dto) throws Exception {
    Registrations registrations = findById(id);
    ScholarshipHolders scholarshipHolder = scholarshipHoldersService.findById(dto.getScholarshipHolderId());
    Courses course = coursesService.findById(dto.getCourseId());

    registrations.setRegistrationDate(dto.getRegistrationDate());
    StatusRegistrations statusRegistration = statusRegistrationsRepository.findById(dto.getStatusId())
        .orElseThrow(() -> new RuntimeException("Erro: Status de inscrição não encontrado."));
    registrations.setStatus(statusRegistration);
    registrations.setScholarshipHolders(scholarshipHolder);
    registrations.setCourses(course);
    return repository.save(registrations);
  }

  public void delete(Integer id) {
    repository.deleteById(id);
  }

}
