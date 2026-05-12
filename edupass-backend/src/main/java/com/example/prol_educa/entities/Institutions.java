package com.example.prol_educa.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "instituicoes")
public class Institutions {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Integer id;

  @Column(name = "nome")
  private String name;

  @Column(name = "rua")
  private String street;

  @Column(name = "numero")
  private String number;

  @Column(name = "complemento")
  private String complement;

  @Column(name = "bairro")
  private String neighborhood;

  @Column(name = "cidade")
  private String city;

  @Column(name = "estado")
  private String state;

  @Column(name = "cep")
  private String cep;

  @Column(name = "tipo")
  private String type;

  @Column(name = "imagem_url")
  private String urlImage;

  @JsonIgnore
  private String password;

  @Column(name = "status")
  private boolean status;

  @Column(name = "cnpj")
  private String cnpj;

  @Column(name = "nome_fantasia")
  private String nomeFantasia;

  @Column(name = "razao_social")
  private String razaoSocial;

  @Column(name = "responsavel_financeiro")
  private String responsavelFinanceiro;

  @Column(name = "telefone_financeiro")
  private String telefoneFinanceiro;

  @Column(name = "segmentos")
  private String segmentos;

  @Column(name = "empresa")
  private String empresa;

  @Column(name = "resp_nome")
  private String respNome;

  @Column(name = "resp_nascimento")
  private String respNascimento;

  @Column(name = "resp_telefone")
  private String respTelefone;

  @Column(name = "resp_email")
  private String respEmail;

  @Column(name = "resp_rg")
  private String respRg;

  @Column(name = "resp_cpf")
  private String respCpf;

  @Column(name = "op_nome")
  private String opNome;

  @Column(name = "op_telefone1")
  private String opTelefone1;

  @Column(name = "op_telefone2")
  private String opTelefone2;

  @Column(name = "op_email")
  private String opEmail;

  // Getters e setters
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

  public String getStreet() {
    return street;
  }

  public void setStreet(String street) {
    this.street = street;
  }

  public String getNumber() {
    return number;
  }

  public void setNumber(String number) {
    this.number = number;
  }

  public String getComplement() {
    return complement;
  }

  public void setComplement(String complement) {
    this.complement = complement;
  }

  public String getNeighborhood() {
    return neighborhood;
  }

  public void setNeighborhood(String neighborhood) {
    this.neighborhood = neighborhood;
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public String getState() {
    return state;
  }

  public void setState(String state) {
    this.state = state;
  }

  public String getCep() {
    return cep;
  }

  public void setCep(String cep) {
    this.cep = cep;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getUrlImage() {
    return urlImage;
  }

  public void setUrlImage(String urlImage) {
    this.urlImage = urlImage;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public boolean isStatus() {
    return status;
  }

  public void setStatus(boolean status) {
    this.status = status;
  }

  public String getCnpj() {
    return cnpj;
  }

  public void setCnpj(String cnpj) {
    this.cnpj = cnpj;
  }

  public String getNomeFantasia() {
    return nomeFantasia;
  }

  public void setNomeFantasia(String nomeFantasia) {
    this.nomeFantasia = nomeFantasia;
  }

  public String getRazaoSocial() {
    return razaoSocial;
  }

  public void setRazaoSocial(String razaoSocial) {
    this.razaoSocial = razaoSocial;
  }

  public String getResponsavelFinanceiro() {
    return responsavelFinanceiro;
  }

  public void setResponsavelFinanceiro(String responsavelFinanceiro) {
    this.responsavelFinanceiro = responsavelFinanceiro;
  }

  public String getTelefoneFinanceiro() {
    return telefoneFinanceiro;
  }

  public void setTelefoneFinanceiro(String telefoneFinanceiro) {
    this.telefoneFinanceiro = telefoneFinanceiro;
  }

  public String getSegmentos() {
    return segmentos;
  }

  public void setSegmentos(String segmentos) {
    this.segmentos = segmentos;
  }

  public String getEmpresa() {
    return empresa;
  }

  public void setEmpresa(String empresa) {
    this.empresa = empresa;
  }

  public String getRespNome() {
    return respNome;
  }

  public void setRespNome(String respNome) {
    this.respNome = respNome;
  }

  public String getRespNascimento() {
    return respNascimento;
  }

  public void setRespNascimento(String respNascimento) {
    this.respNascimento = respNascimento;
  }

  public String getRespTelefone() {
    return respTelefone;
  }

  public void setRespTelefone(String respTelefone) {
    this.respTelefone = respTelefone;
  }

  public String getRespEmail() {
    return respEmail;
  }

  public void setRespEmail(String respEmail) {
    this.respEmail = respEmail;
  }

  public String getRespRg() {
    return respRg;
  }

  public void setRespRg(String respRg) {
    this.respRg = respRg;
  }

  public String getRespCpf() {
    return respCpf;
  }

  public void setRespCpf(String respCpf) {
    this.respCpf = respCpf;
  }

  public String getOpNome() {
    return opNome;
  }

  public void setOpNome(String opNome) {
    this.opNome = opNome;
  }

  public String getOpTelefone1() {
    return opTelefone1;
  }

  public void setOpTelefone1(String opTelefone1) {
    this.opTelefone1 = opTelefone1;
  }

  public String getOpTelefone2() {
    return opTelefone2;
  }

  public void setOpTelefone2(String opTelefone2) {
    this.opTelefone2 = opTelefone2;
  }

  public String getOpEmail() {
    return opEmail;
  }

  public void setOpEmail(String opEmail) {
    this.opEmail = opEmail;
  }

  // Construtores
  public Institutions() {
  }

  public Institutions(String name, String street, String number, String complement, String neighborhood, String city,
      String state, String cep, String type, String urlImage, String password, boolean status) {
    this.name = name;
    this.street = street;
    this.number = number;
    this.complement = complement;
    this.neighborhood = neighborhood;
    this.city = city;
    this.state = state;
    this.cep = cep;
    this.type = type;
    this.urlImage = urlImage;
    this.password = password;
    this.status = status;
  }
}
