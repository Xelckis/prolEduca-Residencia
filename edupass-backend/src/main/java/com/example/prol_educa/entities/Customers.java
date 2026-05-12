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
@Table(name = "Clientes")
public class Customers {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Integer id;

  // === Dados Pessoais ===
  @Column(name = "nome_completo")
  private String fullName;

  @Column(name = "email")
  private String email;

  @Column(name = "telefone")
  private String phone;

  @Column(name = "cpf", unique = true)
  private String cpf;

  @Column(name = "data_nascimento")
  private String dateOfBirth;

  @Column(name = "status")
  private boolean status;

  @JsonIgnore
  @Column(name = "senha")
  private String password;

  // === Endereço ===
  @Column(name = "cep")
  private String cep;

  @Column(name = "logradouro")
  private String logradouro;

  @Column(name = "numero")
  private String numero;

  @Column(name = "complemento")
  private String complemento;

  @Column(name = "bairro")
  private String bairro;

  @Column(name = "cidade")
  private String cidade;

  @Column(name = "estado")
  private String estado;

  // === Empresa ===
  // @Column(name = "empresa")
  // private String empresa;
  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "empresa_id")
  private Companies empresa;

  // @Column(name = "cnpj")
  // private String cnpj;

  @Column(name = "cargo")
  private String cargo;

  @Column(name = "setor")
  private String setor;

  @Column(name = "email_corporativo")
  private String emailCorporativo;

  @Column(name = "telefone_comercial")
  private String telefoneComercial;

  // === Recuperação de Senha ===

  @Column(name = "codigo_recuperacao")
  private String codigoRecuperacao;

  @Column(name = "expiracao_codigo")
  private LocalDateTime expiracaoCodigo;

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

  public Companies getEmpresa() {
    return empresa;
  }

  public void setEmpresa(Companies empresa) {
    this.empresa = empresa;
  }

  // public String getCnpj() {
  // return cnpj;
  // }

  // public void setCnpj(String cnpj) {
  // this.cnpj = cnpj;
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

  public String getEmailCorporativo() {
    return emailCorporativo;
  }

  public void setEmailCorporativo(String emailCorporativo) {
    this.emailCorporativo = emailCorporativo;
  }

  public String getTelefoneComercial() {
    return telefoneComercial;
  }

  public void setTelefoneComercial(String telefoneComercial) {
    this.telefoneComercial = telefoneComercial;
  }

  public String getCodigoRecuperacao() {
    return codigoRecuperacao;
  }

  public void setCodigoRecuperacao(String codigoRecuperacao) {
    this.codigoRecuperacao = codigoRecuperacao;
  }

  public LocalDateTime getExpiracaoCodigo() {
    return expiracaoCodigo;
  }

  public void setExpiracaoCodigo(LocalDateTime expiracaoCodigo) {
    this.expiracaoCodigo = expiracaoCodigo;
  }

  public Set<Roles> getRoles() {
    return roles;
  }

  public void setRoles(Set<Roles> roles) {
    this.roles = roles;
  }

  public Customers() {
  }
}
