package com.example.prol_educa.models;

import java.util.List;

public class InstitutionsFilterDto {
    private Integer id;
    private String name;
    private String cidade;
    private String bairro;

    public InstitutionsFilterDto(Integer id, String name, String cidade, String bairro) {
        this.id = id;
        this.name = name;
        this.cidade = cidade;
        this.bairro = bairro;
    }

    // Getters e Setters
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

    public String getCity() {
        return cidade; 
    }

    public void setCity(String cidade) {
        this.cidade = cidade; 
    }

    public String getNeighborhood() {
        return bairro; 
    }

    public void setNeighborhood(String bairro) {
        this.bairro = bairro; 
    }
}