package com.example.prol_educa.models;

import java.math.BigDecimal;

public class CoursesDto {
	
	private Integer institutionsId;
    private String name;
    private Integer vacancies;
    private BigDecimal percentageScholarship;
    private BigDecimal originalValue;
    private BigDecimal discountValue;
    private String shift;
    private String imageUrl;
    private BigDecimal discountEntrance;
    private boolean status;
    private String description;
	private String scholarshipYear;
    
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getInstitutionsId() {
		return institutionsId;
	}

	public void setInstitutionsId(Integer institutionsId) {
		this.institutionsId = institutionsId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getVacancies() {
		return vacancies;
	}

	public void setVacancies(Integer vacancies) {
		this.vacancies = vacancies;
	}

	public BigDecimal getPercentageScholarship() {
		return percentageScholarship;
	}

	public void setPercentageScholarship(BigDecimal percentageScholarship) {
		this.percentageScholarship = percentageScholarship;
	}

	public BigDecimal getOriginalValue() {
		return originalValue;
	}

	public void setOriginalValue(BigDecimal originalValue) {
		this.originalValue = originalValue;
	}

	public BigDecimal getDiscountValue() {
		return discountValue;
	}

	public void setDiscountValue(BigDecimal discountValue) {
		this.discountValue = discountValue;
	}

	public String getShift() {
		return shift;
	}

	public void setShift(String shift) {
		this.shift = shift;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public BigDecimal getDiscountEntrance() {
		return discountEntrance;
	}

	public void setDiscountEntrance(BigDecimal discountEntrance) {
		this.discountEntrance = discountEntrance;
	}

	public String getScholarshipYear() {
	return scholarshipYear;
	}

	public void setScholarshipYear(String scholarshipYear) {
		this.scholarshipYear = scholarshipYear;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public CoursesDto(Integer institutionsId, String name, Integer vacancies, BigDecimal percentageScholarship,
			BigDecimal originalValue, BigDecimal discountValue, String shift, String imageUrl,
			BigDecimal discountEntrance, boolean status, String description, String scholarshipYear) {
		super();
		this.institutionsId = institutionsId;
		this.name = name;
		this.vacancies = vacancies;
		this.percentageScholarship = percentageScholarship;
		this.originalValue = originalValue;
		this.discountValue = discountValue;
		this.shift = shift;
		this.imageUrl = imageUrl;
		this.discountEntrance = discountEntrance;
		this.status = status;
		this.description = description;
		this.scholarshipYear = scholarshipYear;
	}

	public CoursesDto() {}

}
