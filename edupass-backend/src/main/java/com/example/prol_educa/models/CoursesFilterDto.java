package com.example.prol_educa.models;

import java.util.List;

public class CoursesFilterDto {
    private Integer id;
    private String name;
    private String scholarshipYear;

    public CoursesFilterDto(Integer id, String name, String scholarshipYear) {
        this.id = id;
        this.name = name;
        this.scholarshipYear = scholarshipYear;
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

    public String getScholarshipYear() {
        return scholarshipYear;
    }

    public void setScholarshipYear(String scholarshipYear) {
        this.scholarshipYear = scholarshipYear;
    }
}