package com.example.prol_educa.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import com.example.prol_educa.models.CoursesFilterDto;
import java.util.List;

import com.example.prol_educa.entities.Courses;

@Repository
public interface CoursesRepository extends JpaRepository<Courses, Integer>, JpaSpecificationExecutor<Courses> {
  public List<Courses> findByName(String name);

  public List<Courses> findByShift(String shift);

  public List<Courses> findByPercentageScholarship(BigDecimal percentage_scholarship);

  public List<Courses> findByInstitutions_Name(String name);

  public List<Courses> findByVacanciesGreaterThan(Integer vacancies);

  @Query("SELECT new com.example.prol_educa.models.CoursesFilterDto(c.id, c.name, c.scholarshipYear) FROM Courses c")
	List<CoursesFilterDto> findCursosFilter();
}
