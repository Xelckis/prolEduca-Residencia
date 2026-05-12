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
@Table(name = "Bolsistas")
public class ScholarshipHolders {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Integer id;

  @ManyToOne
  @JoinColumn(name = "cliente_id")
  private Customers customers;

  @Column(name = "nome_completo")
  private String fullName;

  @Column(name = "data_nascimento")
  private String dateOfBirth;

  @Column(name = "necessidades")
  private boolean needs;

  @Column(name = "cpf", unique = true, nullable = false)
  private String cpf;

  @Column(name = "raca_cor")
  private String raceColor;

  public ScholarshipHolders(Customers customers, String fullName, String dateOfBirth, boolean needs, String cpf,
      String raceColor) {
    this.customers = customers;
    this.fullName = fullName;
    this.dateOfBirth = dateOfBirth;
    this.needs = needs;
    this.cpf = cpf;
    this.raceColor = raceColor;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Customers getCustomers() {
    return customers;
  }

  public void setCustomers(Customers customers) {
    this.customers = customers;
  }

  public String getFullName() {
    return fullName;
  }

  public void setFullName(String fullName) {
    this.fullName = fullName;
  }

  public String getDateOfBirth() {
    return dateOfBirth;
  }

  public void setDateOfBirth(String dateOfBirth) {
    this.dateOfBirth = dateOfBirth;
  }

  public boolean isNeeds() {
    return needs;
  }

  public void setNeeds(boolean needs) {
    this.needs = needs;
  }

  public String getCpf() {
    return cpf;
  }

  public void setCpf(String cpf) {
    this.cpf = cpf;
  }

  public String getRaceColor() {
    return raceColor;
  }

  public void setRaceColor(String raceColor) {
    this.raceColor = raceColor;
  }

  public ScholarshipHolders() {
  }

}
