package com.example.prol_educa.entities;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "Empresas")
public class Companies {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Integer id;

  @Column(name = "email")
  private String email;

  @JsonIgnore
  @Column(name = "password")
  private String password;

  @Column(name = "cnpj")
  private String cnpj;

  @Column(name = "nome_fantasia")
  private String fantasyName;

  @Column(name = "numero_telefone")
  private String phoneNumber;

  @ManyToMany(fetch = FetchType.EAGER)
  private Set<Roles> roles = new HashSet<>();

  public Companies() {
  }

  public Companies(String email, String password, String cnpj, String fantasyName, String phoneNumber) {
    this.email = email;
    this.password = password;
    this.cnpj = cnpj;
    this.fantasyName = fantasyName;
    this.phoneNumber = phoneNumber;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getCnpj() {
    return cnpj;
  }

  public void setCnpj(String cnpj) {
    this.cnpj = cnpj;
  }

  public String getFantasyName() {
    return fantasyName;
  }

  public void setFantasyName(String fantasyName) {
    this.fantasyName = fantasyName;
  }

  public String getPhoneNumber() {
    return phoneNumber;
  }

  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  public Set<Roles> getRoles() {
    return roles;
  }

  public void setRoles(Set<Roles> roles) {
    this.roles = roles;
  }
}
