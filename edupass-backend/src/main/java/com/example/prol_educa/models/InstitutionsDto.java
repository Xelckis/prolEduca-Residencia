package com.example.prol_educa.models;

public class InstitutionsDto {

    // Campos já existentes
    private String name;
    private String street;
    private String number;
    private String complement;
    private String neighborhood;
    private String city;
    private String state;
    private String cep;
    private String type;
    private String urlImage;
    private boolean status;
    private String password;

    // Novos campos adicionados
    private String cnpj;
    private String nomeFantasia;
    private String razaoSocial;
    private String responsavelFinanceiro;
    private String telefoneFinanceiro;
    private String segmentos;
    private String empresa;

    private String respNome;
    private String respNascimento;
    private String respTelefone;
    private String respEmail;
    private String respRg;
    private String respCpf;

    private String opNome;
    private String opTelefone1;
    private String opTelefone2;
    private String opEmail;

    // Getters e Setters
    // --- já existentes ---
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getStreet() { return street; }
    public void setStreet(String street) { this.street = street; }

    public String getNumber() { return number; }
    public void setNumber(String number) { this.number = number; }

    public String getComplement() { return complement; }
    public void setComplement(String complement) { this.complement = complement; }

    public String getNeighborhood() { return neighborhood; }
    public void setNeighborhood(String neighborhood) { this.neighborhood = neighborhood; }

    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }

    public String getState() { return state; }
    public void setState(String state) { this.state = state; }

    public String getCep() { return cep; }
    public void setCep(String cep) { this.cep = cep; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public String getUrlImage() { return urlImage; }
    public void setUrlImage(String urlImage) { this.urlImage = urlImage; }

    public boolean isStatus() { return status; }
    public void setStatus(boolean status) { this.status = status; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    // --- novos campos ---
    public String getCnpj() { return cnpj; }
    public void setCnpj(String cnpj) { this.cnpj = cnpj; }

    public String getNomeFantasia() { return nomeFantasia; }
    public void setNomeFantasia(String nomeFantasia) { this.nomeFantasia = nomeFantasia; }

    public String getRazaoSocial() { return razaoSocial; }
    public void setRazaoSocial(String razaoSocial) { this.razaoSocial = razaoSocial; }

    public String getResponsavelFinanceiro() { return responsavelFinanceiro; }
    public void setResponsavelFinanceiro(String responsavelFinanceiro) { this.responsavelFinanceiro = responsavelFinanceiro; }

    public String getTelefoneFinanceiro() { return telefoneFinanceiro; }
    public void setTelefoneFinanceiro(String telefoneFinanceiro) { this.telefoneFinanceiro = telefoneFinanceiro; }

    public String getSegmentos() { return segmentos; }
    public void setSegmentos(String segmentos) { this.segmentos = segmentos; }

    public String getEmpresa() { return empresa; }
    public void setEmpresa(String empresa) { this.empresa = empresa; }

    public String getRespNome() { return respNome; }
    public void setRespNome(String respNome) { this.respNome = respNome; }

    public String getRespNascimento() { return respNascimento; }
    public void setRespNascimento(String respNascimento) { this.respNascimento = respNascimento; }

    public String getRespTelefone() { return respTelefone; }
    public void setRespTelefone(String respTelefone) { this.respTelefone = respTelefone; }

    public String getRespEmail() { return respEmail; }
    public void setRespEmail(String respEmail) { this.respEmail = respEmail; }

    public String getRespRg() { return respRg; }
    public void setRespRg(String respRg) { this.respRg = respRg; }

    public String getRespCpf() { return respCpf; }
    public void setRespCpf(String respCpf) { this.respCpf = respCpf; }

    public String getOpNome() { return opNome; }
    public void setOpNome(String opNome) { this.opNome = opNome; }

    public String getOpTelefone1() { return opTelefone1; }
    public void setOpTelefone1(String opTelefone1) { this.opTelefone1 = opTelefone1; }

    public String getOpTelefone2() { return opTelefone2; }
    public void setOpTelefone2(String opTelefone2) { this.opTelefone2 = opTelefone2; }

    public String getOpEmail() { return opEmail; }
    public void setOpEmail(String opEmail) { this.opEmail = opEmail; }

    // Construtores
    public InstitutionsDto() {}

    public InstitutionsDto(String name, String street, String number, String complement, String neighborhood,
                           String city, String state, String cep, String type, String urlImage, boolean status, String password,
                           String cnpj, String nomeFantasia, String razaoSocial, String responsavelFinanceiro,
                           String telefoneFinanceiro, String segmentos, String empresa,
                           String respNome, String respNascimento, String respTelefone, String respEmail,
                           String respRg, String respCpf, String opNome, String opTelefone1,
                           String opTelefone2, String opEmail) {
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
        this.status = status;
        this.password = password;
        this.cnpj = cnpj;
        this.nomeFantasia = nomeFantasia;
        this.razaoSocial = razaoSocial;
        this.responsavelFinanceiro = responsavelFinanceiro;
        this.telefoneFinanceiro = telefoneFinanceiro;
        this.segmentos = segmentos;
        this.empresa = empresa;
        this.respNome = respNome;
        this.respNascimento = respNascimento;
        this.respTelefone = respTelefone;
        this.respEmail = respEmail;
        this.respRg = respRg;
        this.respCpf = respCpf;
        this.opNome = opNome;
        this.opTelefone1 = opTelefone1;
        this.opTelefone2 = opTelefone2;
        this.opEmail = opEmail;
    }
}
