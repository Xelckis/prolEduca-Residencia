package com.example.prol_educa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.prol_educa.entities.Customers;
import com.example.prol_educa.models.CustomersDto;
import com.example.prol_educa.service.CustomersService;
import com.example.prol_educa.service.FilterHeroService;
import com.example.prol_educa.models.FilterHeroDto;

@RestController
@RequestMapping("/filter")
public class FilterHeroController {

    private final FilterHeroService filterHeroService;

    public FilterHeroController(FilterHeroService filterHeroService) {
        this.filterHeroService = filterHeroService;
    }

    @GetMapping
    public FilterHeroDto getFiltros() {
        return filterHeroService.getFilters();
    }
}