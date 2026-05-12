package com.example.prol_educa.entities;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "clientes_autorizados")
public class AuthorizedUsers {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Integer id;

  // === Dados Pessoais ===
  @Column(name = "nome")
  private String name;

  @Column(name = "cpf", unique = true)
  private String cpf;

  @Column(name = "rg", unique = true)
  private String rg;

  @Column(name = "email")
  private String email;

  @Column(name = "telefone")
  private String phone;

  @Column(name = "estado")
  private String estado;

  @Column(name = "cidade")
  private String cidade;

  @Column(name = "bairro")
  private String bairro;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "empresa_id")
  private Companies empresa;

    // === Papéis/Acessos ===
  @ManyToMany(fetch = FetchType.EAGER)
  // @JoinTable(name = "customer_roles", joinColumns = @JoinColumn(name =
  // "customer_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
  private Set<Roles> roles = new HashSet<>();

  // === Getters e Setters ===
  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getCpf() {
    return cpf;
  }

  public void setCpf(String cpf) {
    this.cpf = cpf;
  }

    public String getRg() {
    return rg;
  }

  public void setRg(String rg) {
    this.rg = rg;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public String getEstado() {
    return estado;
  }

  public void setEstado(String estado) {
    this.estado = estado;
  }

  public String getCidade() {
    return cidade;
  }

  public void setCidade(String cidade) {
    this.cidade = cidade;
  }

  public String getBairro() {
    return bairro;
  }

  public void setBairro(String bairro) {
    this.bairro = bairro;
  }

  public Companies getEmpresa() {
    return empresa;
  }

  public void setEmpresa(Companies empresa) {
    this.empresa = empresa;
  }

  public Set<Roles> getRoles() {
    return roles;
  }

  public void setRoles(Set<Roles> roles) {
    this.roles = roles;
  }

  public AuthorizedUsers() {
  }
}
