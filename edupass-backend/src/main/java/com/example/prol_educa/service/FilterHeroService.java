package com.example.prol_educa.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import com.example.prol_educa.entities.Companies;
import com.example.prol_educa.entities.Customers;
import com.example.prol_educa.entities.Roles;
import com.example.prol_educa.models.CustomersDto;
import com.example.prol_educa.repository.CompaniesRepository;
import com.example.prol_educa.repository.CustomersRepository;
import com.example.prol_educa.repository.RolesRepository;
import com.example.prol_educa.repository.CoursesRepository;
import com.example.prol_educa.repository.InstitutionsRepository;
import com.example.prol_educa.models.FilterHeroDto;
import com.example.prol_educa.models.CoursesFilterDto;
import com.example.prol_educa.models.InstitutionsFilterDto;
import com.example.prol_educa.utils.enuns.ERoles;

@Service
public class FilterHeroService {

    private final CoursesRepository coursesRepository;
    private final InstitutionsRepository institutionsRepository;

    public FilterHeroService(CoursesRepository coursesRepository, InstitutionsRepository institutionsRepository) {
        this.coursesRepository = coursesRepository;
        this.institutionsRepository = institutionsRepository;
    }

    public FilterHeroDto getFilters() {
        List<CoursesFilterDto> cursos = coursesRepository.findAll()
                .stream()
                .map(course -> new CoursesFilterDto(course.getId(), course.getName(), course.getScholarshipYear()))
                .collect(Collectors.toList());

        List<InstitutionsFilterDto> instituicoes = institutionsRepository.findAll()
                .stream()
                .map(inst -> new InstitutionsFilterDto(inst.getId(), inst.getName(), inst.getCity(), inst.getNeighborhood()))
                .collect(Collectors.toList());

        return new FilterHeroDto(cursos, instituicoes);
    }
}