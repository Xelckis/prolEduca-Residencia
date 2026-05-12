package com.example.prol_educa.models;

import java.util.List;

public class FilterHeroDto {
    private List<CoursesFilterDto> cursos;
    private List<InstitutionsFilterDto> instituicoes;

    public FilterHeroDto(List<CoursesFilterDto> cursos, List<InstitutionsFilterDto> instituicoes) {
        this.cursos = cursos;
        this.instituicoes = instituicoes;
    }

    // Getters e Setters
    public List<CoursesFilterDto> getCursos() {
        return cursos;
    }

    public void setCursos(List<CoursesFilterDto> cursos) {
        this.cursos = cursos;
    }

    public List<InstitutionsFilterDto> getInstituicoes() {
        return instituicoes;
    }

    public void setInstituicoes(List<InstitutionsFilterDto> instituicoes) {
        this.instituicoes = instituicoes;
    }
}


