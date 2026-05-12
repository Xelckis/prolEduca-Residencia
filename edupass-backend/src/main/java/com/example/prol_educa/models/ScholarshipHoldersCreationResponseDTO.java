package com.example.prol_educa.models;

public class ScholarshipHoldersCreationResponseDTO {
    
    String message;
    int scholarshipHoldeId;
    
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public int getScholarshipHoldeId() {
        return scholarshipHoldeId;
    }
    public void setScholarshipHoldeId(int scholarshipHoldeId) {
        this.scholarshipHoldeId = scholarshipHoldeId;
    }
    public ScholarshipHoldersCreationResponseDTO(String message, int scholarshipHoldeId) {
        this.message = message;
        this.scholarshipHoldeId = scholarshipHoldeId;
    }
    public ScholarshipHoldersCreationResponseDTO() {}

}
