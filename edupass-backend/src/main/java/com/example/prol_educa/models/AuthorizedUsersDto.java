package com.example.prol_educa.models;

import java.sql.Date;

public class AuthorizedUsersDto {

  // Dados Pessoais
  private String name;
  private String cpf;
  private String rg;
  private String email;
  private String phone;
  private String estado;
  private String cidade;
  private String bairro;
  private Integer empresaId;

  // getters e setters
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

  public Integer getEmpresaId() {
    return empresaId;
  }

  public void setEmpresaId(Integer empresaId) {
    this.empresaId = empresaId;
  }

  public AuthorizedUsersDto() {
  }

  public AuthorizedUsersDto(String name, String cpf, String rg, String email, String phone, String estado, String cidade, String bairro, Integer empresaId) {
    this.name = name;
    this.cpf = cpf;
    this.rg = rg;
    this.email = email;
    this.phone = phone;
    this.estado = estado;
    this.cidade = cidade;
    this.bairro = bairro;
    this.empresaId = empresaId;
  }
}
