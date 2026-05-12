package com.example.prol_educa.entities;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Inscricoes")
public class Registrations {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Integer id;

  @ManyToOne
  @JoinColumn(name = "bolsista_id", nullable = false)
  private ScholarshipHolders scholarshipHolders;

  @ManyToOne
  @JoinColumn(name = "curso_id", nullable = false)
  private Courses courses;

  @Column(name = "data_inscricao")
  private Date registrationDate;

  @ManyToOne
  @JoinColumn(name = "status_id", nullable = false)
  private StatusRegistrations status;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public ScholarshipHolders getScholarshipHolders() {
    return scholarshipHolders;
  }

  public void setScholarshipHolders(ScholarshipHolders scholarshipHolders) {
    this.scholarshipHolders = scholarshipHolders;
  }

  public Courses getCourses() {
    return courses;
  }

  public void setCourses(Courses courses) {
    this.courses = courses;
  }

  public StatusRegistrations getStatus() {
    return status;
  }

  public void setStatus(StatusRegistrations status) {
    this.status = status;
  }

  public Date getRegistrationDate() {
    return registrationDate;
  }

  public void setRegistrationDate(Date registrationDate) {
    this.registrationDate = registrationDate;
  }

  public Registrations(ScholarshipHolders scholarshipHolders, Courses courses, Date registrationDate,
      StatusRegistrations status) {
    super();
    this.scholarshipHolders = scholarshipHolders;
    this.courses = courses;
    this.registrationDate = registrationDate;
    this.status = status;
  }

  public Registrations() {
  }

}
