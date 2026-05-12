package com.example.prol_educa.models;

import java.util.Date;

public class CustomerRegistrationDto {

    // Dados pessoais
    private String name;
    private String email;
    private String confirmationEmail;
    private String password;
    private String confirmationPassword;
    private String cpf;
    private String dateOfBirth;
    private String phone;

    // Endereço
    private String cep;
    private String logradouro;
    private String numero;
    private String complemento;
    private String bairro;
    private String cidade;
    private String estado;

    // Empresa
    private String empresa;
    private String cnpj;
    private String cargo;
    private String setor;
    private String email_corporativo;
    private String telefone_comercial;

    // ========== Getters e Setters ==========

    // --- Dados pessoais
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getConfirmationEmail() {
        return confirmationEmail;
    }
    public void setConfirmationEmail(String confirmationEmail) {
        this.confirmationEmail = confirmationEmail;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmationPassword() {
        return confirmationPassword;
    }
    public void setConfirmationPassword(String confirmationPassword) {
        this.confirmationPassword = confirmationPassword;
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

    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
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
    public String getEmpresa() {
        return empresa;
    }
    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public String getCnpj() {
        return cnpj;
    }
    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

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

    // ========== Construtores ==========

    public CustomerRegistrationDto() {
        super();
    }

    public CustomerRegistrationDto(String name, String email, String confirmationEmail, String password,
                                   String confirmationPassword, String cpf, String dateOfBirth, String phone,
                                   String cep, String logradouro, String numero, String complemento, String bairro,
                                   String cidade, String estado, String empresa, String cnpj, String cargo,
                                   String setor, String email_corporativo, String telefone_comercial) {
        this.name = name;
        this.email = email;
        this.confirmationEmail = confirmationEmail;
        this.password = password;
        this.confirmationPassword = confirmationPassword;
        this.cpf = cpf;
        this.dateOfBirth = dateOfBirth;
        this.phone = phone;
        this.cep = cep;
        this.logradouro = logradouro;
        this.numero = numero;
        this.complemento = complemento;
        this.bairro = bairro;
        this.cidade = cidade;
        this.estado = estado;
        this.empresa = empresa;
        this.cnpj = cnpj;
        this.cargo = cargo;
        this.setor = setor;
        this.email_corporativo = email_corporativo;
        this.telefone_comercial = telefone_comercial;
    }
}
