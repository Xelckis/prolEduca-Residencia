package com.example.prol_educa.models;

public class ValidateCodeDto {
    
    private String email;
    private String code;

    // Construtor vazio
    public ValidateCodeDto() {}

    // Getters e Setters
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
