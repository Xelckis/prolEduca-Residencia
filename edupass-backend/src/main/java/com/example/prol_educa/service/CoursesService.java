package com.example.prol_educa.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.example.prol_educa.entities.Courses;
import com.example.prol_educa.entities.Institutions;
import com.example.prol_educa.models.CoursesDto;
import com.example.prol_educa.repository.CoursesRepository;

@Service
public class CoursesService {

  @Autowired
  public CoursesRepository repository;

  @Autowired
  public InstitutionsService institutionsService;

  public void create(CoursesDto dto) throws Exception {
    Institutions institution = institutionsService.findById(dto.getInstitutionsId());
    Courses course = new Courses();
    
    course.setInstitutions(institution);
    course.setName(dto.getName());
    course.setVacancies(dto.getVacancies());
    course.setPercentageScholarship(dto.getPercentageScholarship());
    course.setOriginalValue(dto.getOriginalValue());
    course.setDiscountValue(dto.getDiscountValue());
    course.setShift(dto.getShift());
    course.setImageUrl(dto.getImageUrl());
    course.setDiscountEntrance(dto.getDiscountEntrance());
    course.setStatus(dto.isStatus());
    course.setDescription(dto.getDescription());
    course.setScholarshipYear(dto.getScholarshipYear());

    repository.save(course);
  }

  public void reduceVacancies(Integer id) throws Exception {
    Courses course = findById(id);

    if (course.getVacancies() > 0) {
      course.setVacancies(course.getVacancies() - 1);
    }

    repository.save(course);
  }

  public List<Courses> findAll() {
    return repository.findAll();
  }

  public List<Courses> findAllFromVacancies(Integer amount) {
    return repository.findByVacanciesGreaterThan(amount);
  }

  public Courses findById(Integer id) throws Exception {
    Optional<Courses> course = repository.findById(id);
    if (!course.isPresent()) {
      throw new Exception("Curso não encontrado");
    }

    return course.get();
  }

  public Courses update(Integer id, CoursesDto dto) throws Exception {
    Institutions institution = institutionsService.findById(dto.getInstitutionsId());
    Courses course = findById(id);
    course.setInstitutions(institution);
    course.setName(dto.getName());
    course.setVacancies(dto.getVacancies());
    course.setPercentageScholarship(dto.getPercentageScholarship());
    course.setOriginalValue(dto.getOriginalValue());
    course.setDiscountValue(dto.getDiscountValue());
    course.setShift(dto.getShift());
    course.setImageUrl(dto.getImageUrl());
    course.setDiscountEntrance(dto.getDiscountEntrance());
    course.setStatus(dto.isStatus());
    course.setDescription(dto.getDescription());
    course.setScholarshipYear(dto.getScholarshipYear());

    return repository.save(course);
  }

  public void delete(Integer id) {
    repository.deleteById(id);
  }

  public List<Courses> findByName(String name) {
    List<Courses> courses = repository.findByName(name);
    return courses;
  }

  public List<Courses> findByShift(String shift) {
    List<Courses> courses = repository.findByShift(shift);
    return courses;
  }

  public List<Courses> findByPercentageScholarship(BigDecimal percentage) {
    List<Courses> courses = repository.findByPercentageScholarship(percentage);
    return courses;
  }

  public List<Courses> findByInstituitions_Name(String name) {
    List<Courses> course = repository.findByInstitutions_Name(name);
    return course;
  }

  public List<Courses> filter(Map<String, String> filters) {
    Specification<Courses> specification = Specification.where(null);

    for (Map.Entry<String, String> entry : filters.entrySet()) {

      String key = entry.getKey();
      String value = entry.getValue();

      specification = specification.and((root, query, criteriaBuilder) -> {

        switch (key) {
        case "name":
          return criteriaBuilder.like(criteriaBuilder.lower(root.get("name")), "%" + value.toLowerCase() + "%");

        case "institutions":
          return criteriaBuilder.like(criteriaBuilder.lower(root.get("institutions").get("name")),
              "%" + value.toLowerCase() + "%");

        case "shift":
          return criteriaBuilder.equal(criteriaBuilder.lower(root.get("shift")), value.toLowerCase());

        case "minPercentageScholarship":
          try {
            BigDecimal minDiscount = new BigDecimal(value);
            return criteriaBuilder.greaterThanOrEqualTo(root.get("percentageScholarship"), minDiscount);
          } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Desconto mínimo inválido: " + value);
          }

        case "city":
          return criteriaBuilder.like(criteriaBuilder.lower(root.get("city")), value.toLowerCase());

        case "category":
          return criteriaBuilder.like(criteriaBuilder.lower(root.get("category")), value.toLowerCase());

        default:
          throw new IllegalArgumentException("Campo de filtro inválido: " + key);
        }
      });
    }

    return repository.findAll(specification);
  }
}
