package com.example.prol_educa.models;

import java.sql.Date;

public class CustomersDto {

  // Dados Pessoais
  private String fullName;
  private String email;
  private String phone;
  private String cpf;
  private String dateOfBirth;
  private boolean status;
  private String password;

  // Endereço
  private String cep;
  private String logradouro;
  private String numero;
  private String complemento;
  private String bairro;
  private String cidade;
  private String estado;

  // Empresa
  private Integer empresaId;
  // private String cnpj;
  private String cargo;
  private String setor;
  private String email_corporativo;
  private String telefone_comercial;

  // getters e setters
  public String getFullName() {
    return fullName;
  }

  public void setFullName(String fullName) {
    this.fullName = fullName;
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

  public String getCpf() {
    return cpf;
  }

  public void setCpf(String cpf) {
    this.cpf = cpf;
  }

  public String getDateOfBirth() {
    return dateOfBirth;
  }

  public void setDateOfBirth(String dateOfBirth) {
    this.dateOfBirth = dateOfBirth;
  }

  public boolean isStatus() {
    return status;
  }

  public void setStatus(boolean status) {
    this.status = status;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  // --- Endereço
  public String getCep() {
    return cep;
  }

  public void setCep(String cep) {
    this.cep = cep;
  }

  public String getLogradouro() {
    return logradouro;
  }

  public void setLogradouro(String logradouro) {
    this.logradouro = logradouro;
  }

  public String getNumero() {
    return numero;
  }

  public void setNumero(String numero) {
    this.numero = numero;
  }

  public String getComplemento() {
    return complemento;
  }

  public void setComplemento(String complemento) {
    this.complemento = complemento;
  }

  public String getBairro() {
    return bairro;
  }

  public void setBairro(String bairro) {
    this.bairro = bairro;
  }

  public String getCidade() {
    return cidade;
  }

  public void setCidade(String cidade) {
    this.cidade = cidade;
  }

  public String getEstado() {
    return estado;
  }

  public void setEstado(String estado) {
    this.estado = estado;
  }

  // --- Empresa
  public Integer getEmpresaId() {
    return empresaId;
  }

  public void setEmpresaId(Integer empresaId) {
    this.empresaId = empresaId;
  }

  // public String getCnpj() {
  //   return cnpj;
  // }

  // public void setCnpj(String cnpj) {
  //   this.cnpj = cnpj;
  // }

  public String getCargo() {
    return cargo;
  }

  public void setCargo(String cargo) {
    this.cargo = cargo;
  }

  public String getSetor() {
    return setor;
  }

  public void setSetor(String setor) {
    this.setor = setor;
  }

  public String getEmail_corporativo() {
    return email_corporativo;
  }

  public void setEmail_corporativo(String email_corporativo) {
    this.email_corporativo = email_corporativo;
  }

  public String getTelefone_comercial() {
    return telefone_comercial;
  }

  public void setTelefone_comercial(String telefone_comercial) {
    this.telefone_comercial = telefone_comercial;
  }

  // =================== Construtores ===================

  public CustomersDto() {
  }

  public CustomersDto(String fullName, String email, String phone, String cpf, String dateOfBirth, boolean status,
      String password, String cep, String logradouro, String numero, String complemento, String bairro, String cidade,
      String estado, Integer empresaId, /*String cnpj,*/ String cargo, String setor, String email_corporativo,
      String telefone_comercial) {
    this.fullName = fullName;
    this.email = email;
    this.phone = phone;
    this.cpf = cpf;
    this.dateOfBirth = dateOfBirth;
    this.status = status;
    this.password = password;
    this.cep = cep;
    this.logradouro = logradouro;
    this.numero = numero;
    this.complemento = complemento;
    this.bairro = bairro;
    this.cidade = cidade;
    this.estado = estado;
    this.empresaId = empresaId;
    // this.cnpj = cnpj;
    this.cargo = cargo;
    this.setor = setor;
    this.email_corporativo = email_corporativo;
    this.telefone_comercial = telefone_comercial;
  }
}
