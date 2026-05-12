package com.example.prol_educa.models;

public class CompaniesDto {

  private String email;
  private String password;
  private String cnpj;
  private String fantasyName;
  private String phoneNumber;

  public CompaniesDto() {
  }

  public CompaniesDto(String email, String password, String cnpj, String fantasyName, String phoneNumber) {
    this.email = email;
    this.password = password;
    this.cnpj = cnpj;
    this.fantasyName = fantasyName;
    this.phoneNumber = phoneNumber;
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
}
