package com.example.prol_educa.entities;

import com.example.prol_educa.utils.enuns.EStatusRegistration;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "status_inscricao")
public class StatusRegistrations {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Integer id;

  @Enumerated(EnumType.STRING)
  private EStatusRegistration status;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public EStatusRegistration getStatus() {
    return status;
  }

  public void setStatus(EStatusRegistration status) {
    this.status = status;
  }

  public StatusRegistrations(EStatusRegistration status) {
    super();
    this.status = status;
  }

  public StatusRegistrations() {
  }

}